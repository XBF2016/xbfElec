package com.xbf.elec.web.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.jbpm.api.ProcessDefinition;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xbf.elec.dao.util.Conditions;
import com.xbf.elec.dao.util.Conditions.Operator;
import com.xbf.elec.domain.ElecApply;
import com.xbf.elec.domain.ElecApplyTemplate;
import com.xbf.elec.domain.ElecUser;
import com.xbf.elec.domain.helper.ProcDefAppTemplate;
import com.xbf.elec.service.ElecJBPMService;

public class ElecApplyAction extends ActionSupport implements
		ModelDriven<ElecApply> {
	private ElecJBPMService jbpmService;
	private ElecApply apply = new ElecApply();

	// 文件上传三要素
	private File upload;
	private String uploadFileName;
	private String uploadContentType;

	// 起草申请的首页
	public String home() {
		// 把数据带过去
		// 流程定义的Key下最新的流程定义列表
		List<ProcessDefinition> processDefinitionList = jbpmService
				.listNewestProcessDefinitions();
		// 所有的申请模板
		List<ElecApplyTemplate> applyTemplateList = jbpmService
				.listApplyTemplate();

		// 数据的关联与组合
		List<ProcDefAppTemplate> pdatList = new ArrayList<ProcDefAppTemplate>();
		// 因为流程定义更多所以先迭代
		for (ProcessDefinition processDefinition : processDefinitionList) {
			// 流程的数据
			String processDefinitionId = processDefinition.getId();
			String processDefinitionKey = processDefinition.getKey();
			// 模板的数据
			String templateId = null;
			String filename = null;
			String path = null;
			for (ElecApplyTemplate applyTemplate : applyTemplateList) {
				templateId = applyTemplate.getTemplateId();
				filename = applyTemplate.getFilename();
				path = applyTemplate.getPath();
			}
			// 组合数据到辅助类
			ProcDefAppTemplate pdat = new ProcDefAppTemplate();
			pdat.setFilename(filename);
			pdat.setPath(path);
			pdat.setProcessDefinitionId(processDefinitionId);
			pdat.setProcessDefinitionKey(processDefinitionKey);
			pdat.setTemplateId(templateId);
			// 放到List中
			pdatList.add(pdat);
		}

		ServletActionContext.getRequest().setAttribute("pdatList", pdatList);
		return "home";
	}

	// 提交申请并启动流程
	public String start() {
		// 获取用户
		ElecUser user = (ElecUser) ServletActionContext.getRequest()
				.getSession().getAttribute("user");

		// 文件上传相关
		// 上传到哪里？
		String path = ServletActionContext.getServletContext().getRealPath(
				"/upload/applyFile");
		// 确定该路径是否存在
		File file = new File(path);
		// 如果不存在就创建此路径
		if (!file.exists()) {
			file.mkdirs();
		}

		// 确定最终的位置（路径+唯一标识+文件名）
		path += ("\\" + UUID.randomUUID() + uploadFileName);
		// 复制（新方式：重命名就可）
		upload.renameTo(new File(path));

		// 完善申请信息
		apply.setAccount(user.getAccount());
		apply.setApplyStatus("applyStatus_ing");
		apply.setApplyTime(new Date());
		apply.setFilename(uploadFileName);
		apply.setPath(path);
		apply.setUserId(user.getUserId());
		apply.setUsername(user.getUsername());

		// 调用JBPMService保存申请信息并启动流程
		jbpmService.addApplyAndStartProcessInstance(apply);
		return "start";
	}

	// 我的申请
	public String applyMy() {
		// 获取用户
		ElecUser user = (ElecUser) ServletActionContext.getRequest()
				.getSession().getAttribute("user");

		// 所有的流程定义
		List<ProcessDefinition> processDefinitionList = jbpmService
				.listNewestProcessDefinitions();
		// 所有申请信息（条件查询）
		Conditions conditions = new Conditions();
		conditions.addCondition("userId", user.getUserId(), Operator.EQUAL);
		conditions.addCondition("processDefinitionKey", apply
				.getProcessDefinitionKey(), Operator.EQUAL);
		conditions.addCondition("applyStatus", apply.getApplyStatus(),
				Operator.EQUAL);
		List<ElecApply> applyList = jbpmService.listApply(conditions);

		ServletActionContext.getRequest().setAttribute("processDefinitionList",
				processDefinitionList);
		ServletActionContext.getRequest().setAttribute("applyList", applyList);
		return "applyMy";
	}

	public synchronized ElecJBPMService getJbpmService() {
		return jbpmService;
	}

	public synchronized void setJbpmService(ElecJBPMService jbpmService) {
		this.jbpmService = jbpmService;
	}

	public ElecApply getModel() {
		return apply;
	}

	public synchronized File getUpload() {
		return upload;
	}

	public synchronized void setUpload(File upload) {
		this.upload = upload;
	}

	public synchronized String getUploadFileName() {
		return uploadFileName;
	}

	public synchronized void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public synchronized String getUploadContentType() {
		return uploadContentType;
	}

	public synchronized void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

}
