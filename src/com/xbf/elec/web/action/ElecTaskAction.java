package com.xbf.elec.web.action;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.xbf.elec.domain.ElecUser;
import com.xbf.elec.domain.helper.ApproveInfo;
import com.xbf.elec.domain.helper.TaskApply;
import com.xbf.elec.service.ElecJBPMService;

public class ElecTaskAction {
	private ElecJBPMService jbpmService;
	private String taskId;
	private String comment;
	private String outcome;
	private boolean isAgree;
	// 下载文件必须的输入流
	private InputStream inputStream;

	// 待我审批主页
	public String taskMy() {
		// 获取当前登录用户
		ElecUser user = (ElecUser) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		// 查询当前用户要处理的任务
		List<TaskApply> taskApplyList = jbpmService.listTaskApply(user);

		ServletActionContext.getRequest().setAttribute("taskApplyList",
				taskApplyList);
		return "taskMy";
	}

	// 审批页面
	public String taskApprove() {
		// 根据传过来的taskId，查询出Task，并根据taskId可以查出流程变量apply，将task和apply组合到辅助变量中即可
		TaskApply taskApply = jbpmService.findTaskApplyByTaskId(taskId);
		ServletActionContext.getRequest().setAttribute("taskApply", taskApply);
		return "taskApprove";
	}

	// 下载申请的文件
	public String downloadApplyFile() {
		// 利用现成的方法
		TaskApply taskApply = jbpmService.findTaskApplyByTaskId(taskId);
		String filename = taskApply.getFilename();
		try {
			// 输入流连到文件所在的位置
			inputStream = new FileInputStream(taskApply.getPath());
			// 拿到mime类型（在struts-workflow.xml中使用）
			String contentType = ServletActionContext.getServletContext()
					.getMimeType(taskApply.getFilename());
			ServletActionContext.getRequest().setAttribute("contentType",
					contentType);
			// 拿到用户名（在struts-workflow.xml中使用）
			ServletActionContext.getRequest().setAttribute("filename",
					URLEncoder.encode(filename, "utf-8"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return "downloadApplyFile";
	}

	// 审批（根据isApprove判断是否结束任务）
	public String approve() {
		// 获取当前登录用户
		ElecUser user = (ElecUser) ServletActionContext.getRequest()
				.getSession().getAttribute("user");
		jbpmService.approve(user, isAgree, taskId, outcome, comment);
		return "approve";
	}

	// 查看审批信息（流程记录）
	public String approveInfo() {
		List<ApproveInfo> approveInfos = jbpmService.listApproveInfos(taskId);
		ServletActionContext.getRequest().setAttribute("approveInfos",
				approveInfos);
		return "approveInfo";
	}

	public synchronized ElecJBPMService getJbpmService() {
		return jbpmService;
	}

	public synchronized void setJbpmService(ElecJBPMService jbpmService) {
		this.jbpmService = jbpmService;
	}

	public synchronized String getTaskId() {
		return taskId;
	}

	public synchronized void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public synchronized InputStream getInputStream() {
		return inputStream;
	}

	public synchronized void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public synchronized String getComment() {
		return comment;
	}

	public synchronized void setComment(String comment) {
		this.comment = comment;
	}

	public synchronized String getOutcome() {
		return outcome;
	}

	public synchronized void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public synchronized boolean getIsAgree() {
		return isAgree;
	}

	public synchronized void setIsAgree(boolean isAgree) {
		this.isAgree = isAgree;
	}

}
