package com.xbf.elec.domain.helper;

import java.io.Serializable;

//审批信息（流程记录）辅助类
public class ApproveInfo implements Serializable {
	private String taskName;
	private String userId;
	private String username;
	private String account;
	private String comment;

	public synchronized String getTaskName() {
		return taskName;
	}

	public synchronized void setTaskName(String taskName) {
		this.taskName = taskName;
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

	public synchronized String getComment() {
		return comment;
	}

	public synchronized void setComment(String comment) {
		this.comment = comment;
	}

}
