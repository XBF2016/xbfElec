package com.xbf.elec.domain.helper;

import java.util.Set;

//辅助类，用于存储任务与申请信息的组合信息
public class TaskApply {
	private String taskId;
	private String applyId;
	private String taskName;
	private String processDefinitionKey;
	private String processDefinitionId;
	private String userId;
	private String username;
	private String account;
	private String filename;
	private String path;
	private Set<String> outcomes;

	public synchronized String getTaskId() {
		return taskId;
	}

	public synchronized void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public synchronized String getTaskName() {
		return taskName;
	}

	public synchronized void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public synchronized String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public synchronized void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	public synchronized String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public synchronized void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public synchronized String getUserId() {
		return userId;
	}

	public synchronized void setUserId(String userId) {
		this.userId = userId;
	}

	public synchronized String getUsername() {
		return username;
	}

	public synchronized void setUsername(String username) {
		this.username = username;
	}

	public synchronized String getAccount() {
		return account;
	}

	public synchronized void setAccount(String account) {
		this.account = account;
	}

	public synchronized String getApplyId() {
		return applyId;
	}

	public synchronized void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public synchronized String getFilename() {
		return filename;
	}

	public synchronized void setFilename(String filename) {
		this.filename = filename;
	}

	public synchronized String getPath() {
		return path;
	}

	public synchronized void setPath(String path) {
		this.path = path;
	}

	public synchronized Set<String> getOutcomes() {
		return outcomes;
	}

	public synchronized void setOutcomes(Set<String> outcomes) {
		this.outcomes = outcomes;
	}

}
