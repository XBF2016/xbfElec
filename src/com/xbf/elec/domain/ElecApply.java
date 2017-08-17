package com.xbf.elec.domain;

import java.io.Serializable;
import java.util.Date;

public class ElecApply implements Serializable {
	/*
	 * #申请信息表 create table elecApply( applyId varchar(100) primary key, userId
	 * varchar(100), username varchar(50), account varchar(50),
	 * processDefinitionId varchar(100), processDefinitionKey varchar(100),
	 * applyTime date, applyStatus varchar(50), filename varchar(100), path text
	 * );
	 */

	private String applyId;
	private String userId;
	private String username;
	private String account;
	private String processDefinitionId;
	private String processDefinitionKey;
	private Date applyTime;
	private String filename;
	private String path;
	private String applyStatus;

	public synchronized String getApplyId() {
		return applyId;
	}

	public synchronized void setApplyId(String applyId) {
		this.applyId = applyId;
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

	public synchronized String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public synchronized void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public synchronized String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public synchronized void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	public synchronized Date getApplyTime() {
		return applyTime;
	}

	public synchronized void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
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

	public synchronized String getApplyStatus() {
		return applyStatus;
	}

	public synchronized void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}

}
