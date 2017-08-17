package com.xbf.elec.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipInputStream;

import org.jbpm.api.Execution;
import org.jbpm.api.NewDeployment;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;

import com.xbf.elec.dao.IElecApplyDAO;
import com.xbf.elec.dao.IElecApplyTemplateDAO;
import com.xbf.elec.dao.util.Conditions;
import com.xbf.elec.dao.util.Conditions.Operator;
import com.xbf.elec.domain.ElecApply;
import com.xbf.elec.domain.ElecApplyTemplate;
import com.xbf.elec.domain.ElecRole;
import com.xbf.elec.domain.ElecUser;
import com.xbf.elec.domain.helper.ApproveInfo;
import com.xbf.elec.domain.helper.TaskApply;

public class ElecJBPMService {
	// 引擎
	ProcessEngine processEngine;
	// 申请模板文件管理DAO
	private IElecApplyTemplateDAO applyTemplateDAO;
	// 申请信息DAO
	private IElecApplyDAO applyDAO;

	public synchronized ProcessEngine getProcessEngine() {
		return processEngine;
	}

	public synchronized void setProcessEngine(ProcessEngine processEngine) {
		this.processEngine = processEngine;
	}

	public void depolyment(File upload) {
		NewDeployment newDeployment = processEngine.getRepositoryService()
				.createDeployment();
		// 加载上传的zip文件
		try {
			// 天机资源
			newDeployment.addResourcesFromZipInputStream(new ZipInputStream(
					new FileInputStream(upload)));
			// 部署
			newDeployment.deploy();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("部署流程失败" + e);
		}
	}

	public List listNewestProcessDefinitions() {
		// 查询所有并按照版本排序
		List<ProcessDefinition> list = processEngine.getRepositoryService()
				.createProcessDefinitionQuery().orderAsc(
						(ProcessDefinitionQuery.PROPERTY_VERSION)).list();

		// 一个流程名一个流程对象
		Map<String, ProcessDefinition> processDefinitionMapByKey = new HashMap<String, ProcessDefinition>();

		// 最后每个key对应的value都是最新的（因为按照升序排序）
		if (list != null) {
			for (ProcessDefinition processDefinition : list) {
				processDefinitionMapByKey.put(processDefinition.getKey(),
						processDefinition);
			}
			return new ArrayList(processDefinitionMapByKey.values());
		}
		return null;
	}

	public void deleteByProcessDefinitionId(String id) {
		// 根据id查找流程定义对象
		ProcessDefinition processDefinition = processEngine
				.getRepositoryService().createProcessDefinitionQuery()
				.processDefinitionId(id).uniqueResult();

		if (processDefinition != null) {
			// 查找该流程定义对象所属流程
			// 只能删除部署对象，关联着删除了流程，因为他们一对一
			processEngine.getRepositoryService().deleteDeployment(
					processDefinition.getDeploymentId());
		}

	}

	public InputStream showProcessImg(String id) {
		// 根据id查找流程定义对象
		ProcessDefinition processDefinition = processEngine
				.getRepositoryService().createProcessDefinitionQuery()
				.processDefinitionId(id).uniqueResult();

		if (processDefinition != null) {
			// 根据部署id和文件名得到流，这个两个东西可以从流程对象拿到
			return processEngine.getRepositoryService().getResourceAsStream(
					processDefinition.getDeploymentId(),
					processDefinition.getImageResourceName());
		}
		return null;
	}

	public synchronized IElecApplyTemplateDAO getApplyTemplateDAO() {
		return applyTemplateDAO;
	}

	public synchronized void setApplyTemplateDAO(
			IElecApplyTemplateDAO applyTemplateDAO) {
		this.applyTemplateDAO = applyTemplateDAO;
	}

	public void addOrUpdateApplyTemplate(ElecApplyTemplate applyTemplate) {
		// 查询与该流程定义对应的模板
		Conditions conditions = new Conditions();
		conditions.addCondition("processDefinitionKey", applyTemplate
				.getProcessDefinitionKey(), Operator.EQUAL);
		List<ElecApplyTemplate> applyTemplates = applyTemplateDAO
				.findByConditions(conditions);
		// 如果有记录则删除
		if (applyTemplates != null && applyTemplates.size() > 0) {
			applyTemplateDAO.deleteAll(applyTemplates);
		}
		applyTemplateDAO.addOrUpdate(applyTemplate);
	}

	public List<ElecApplyTemplate> listApplyTemplate() {
		return applyTemplateDAO.findAll();
	}

	public ElecApplyTemplate getApplyTemplateById(String templateId) {
		return applyTemplateDAO.findById(templateId);
	}

	public void deleteApplyTemplateById(String templateId) {
		applyTemplateDAO.deleteById(templateId);
	}

	public synchronized IElecApplyDAO getApplyDAO() {
		return applyDAO;
	}

	public synchronized void setApplyDAO(IElecApplyDAO applyDAO) {
		this.applyDAO = applyDAO;
	}

	public void addApplyAndStartProcessInstance(ElecApply apply) {
		// 保存
		applyDAO.addOrUpdate(apply);

		// 添加流程变量：申请人
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("account", apply.getAccount());
		// 每次启动流程时就将申请信息当做变量放入整个流程中
		variables.put("apply", apply);
		// 启动流程实例
		ProcessInstance processInstance = processEngine.getExecutionService()
				.startProcessInstanceById(apply.getProcessDefinitionId(),
						variables);

		// 完成第一个任务
		// 拿到任务
		Task task = processEngine.getTaskService().createTaskQuery()
				.processInstanceId(processInstance.getId()).uniqueResult();
		// 完成
		processEngine.getTaskService().completeTask(task.getId());
	}

	public List<ElecApply> listApply(Conditions conditions) {
		return applyDAO.findByConditions(conditions);
	}

	// 查询我的任务
	public List<Task> myTask(ElecUser user) {
		// 获取用户的所有角色
		Set<ElecRole> roles = user.getRoles();
		// 该用户所有角色的所有任务
		List<Task> allTaskList = new ArrayList<Task>();
		// 遍历roles，用TaskService查询每一个角色名对应的个人任务
		for (ElecRole role : roles) {
			List<Task> taskList = processEngine.getTaskService()
					.findPersonalTasks(role.getRoleName());
			if (taskList != null) {
				allTaskList.addAll(taskList);
			}
		}
		return allTaskList;

	}

	public List<TaskApply> listTaskApply(ElecUser user) {
		// 拿到当前用户的所有代办任务
		List<Task> taskList = myTask(user);
		// 辅助类：任务及其申请信息
		List<TaskApply> taskAppyList = new ArrayList<TaskApply>();
		if (taskList != null) {
			for (Task task : taskList) {
				// 用TaskService根据任务id查询申请信息
				ElecApply apply = (ElecApply) processEngine.getTaskService()
						.getVariable(task.getId(), "apply");
				// 填充数据
				TaskApply taskApply = new TaskApply();
				taskApply.setAccount(apply.getAccount());
				taskApply
						.setProcessDefinitionId(apply.getProcessDefinitionId());
				taskApply.setProcessDefinitionKey(apply
						.getProcessDefinitionKey());
				taskApply.setTaskId(task.getId());
				taskApply.setTaskName(task.getName());
				taskApply.setUserId(apply.getUserId());
				taskApply.setUsername(apply.getUsername());
				taskApply.setApplyId(apply.getApplyId());
				// 添加
				taskAppyList.add(taskApply);
			}
		}

		return taskAppyList;
	}

	public TaskApply findTaskApplyByTaskId(String taskId) {
		// 拿到apply
		ElecApply apply = (ElecApply) processEngine.getTaskService()
				.getVariable(taskId, "apply");
		// 拿到Task
		Task task = processEngine.getTaskService().getTask(taskId);
		// 辅助类
		TaskApply taskApply = new TaskApply();
		// Outcomes
		Set<String> outcomes = processEngine.getTaskService().getOutcomes(
				taskId);
		// 封装数据
		taskApply.setAccount(apply.getAccount());
		taskApply.setProcessDefinitionId(apply.getProcessDefinitionId());
		taskApply.setProcessDefinitionKey(apply.getProcessDefinitionKey());
		taskApply.setTaskId(task.getId());
		taskApply.setTaskName(task.getName());
		taskApply.setUserId(apply.getUserId());
		taskApply.setUsername(apply.getUsername());
		taskApply.setFilename(apply.getFilename());
		taskApply.setOutcomes(outcomes);
		taskApply.setPath(apply.getPath());
		taskApply.setApplyId(apply.getApplyId());

		return taskApply;
	}

	public void approve(ElecUser user, boolean isAgree, String taskId,
			String outcome, String comment) {
		// 拿到apply
		ElecApply apply = (ElecApply) processEngine.getTaskService()
				.getVariable(taskId, "apply");
		// 流程变量
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("outcome", outcome);
		// 执行id
		String executionId = processEngine.getTaskService().getTask(taskId)
				.getExecutionId();
		Execution execution = processEngine.getExecutionService()
				.findExecutionById(executionId);
		String processInstanceId = execution.getProcessInstance().getId();

		// 如果同意
		if (isAgree) {
			// 如果同意，则添加流程记录
			ApproveInfo approveInfo = new ApproveInfo();
			// 谁的审批意见？
			approveInfo.setAccount(user.getAccount());
			approveInfo.setUserId(user.getUserId());
			approveInfo.setUsername(user.getUsername());
			// 当前的任务名？
			Task task = processEngine.getTaskService().getTask(taskId);
			approveInfo.setTaskName(task.getName());
			// 审批意见？
			approveInfo.setComment(comment);

			// 整个流程下来不止一个审批意见
			List<ApproveInfo> approveInfos = (List<ApproveInfo>) processEngine
					.getTaskService().getVariable(taskId, "approveInfos");
			if (approveInfos == null) {
				approveInfos = new ArrayList<ApproveInfo>();
			}
			// 将这个流程记录放到总记录里面
			approveInfos.add(approveInfo);

			// 存放流程变量
			variables.put("approveInfos", approveInfos);

			processEngine.getTaskService().completeTask(taskId, outcome,
					variables);
			// 检查流程是否结束
			ProcessInstance processInstance = processEngine
					.getExecutionService().findProcessInstanceById(
							processInstanceId);
			// 如果流程实例不存在说明流程已经结束
			if (processInstance == null) {
				// 结束之后设置状态
				apply.setApplyStatus("applyStatus_pass");
				// 持久化到数据库
				applyDAO.addOrUpdate(apply);
			}

		} else {
			// 如果不同意就结束流程实例
			// 首先要获取流程实例的Id
			processEngine.getExecutionService().endProcessInstance(
					processInstanceId, ProcessInstance.STATE_ENDED);
			// 结束之后设置状态
			apply.setApplyStatus("applyStatus_fail");
			// 持久化到数据库
			applyDAO.addOrUpdate(apply);
		}
	}

	// 根据taskId查看该流程的所有审批意见
	public List<ApproveInfo> listApproveInfos(String taskId) {
		List<ApproveInfo> approveInfos = (List<ApproveInfo>) processEngine
				.getTaskService().getVariable(taskId, "approveInfos");
		return approveInfos;
	}
}
