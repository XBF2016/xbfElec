package com.xbf.elec.domain;

/*
 #权限表
 create table elecFunction(
 funtionId varchar(100) primary key,
 path varchar(255) unique,#请求路径
 funtionName varchar(50) not null,#权限名称
 groups varchar(50) not null#组名
 );
 */
public class ElecFunction {
	private String functionId;
	private String functionName;
	private String path;
	private String groups;

	public synchronized String getFunctionId() {
		return functionId;
	}

	public synchronized void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public synchronized String getFunctionName() {
		return functionName;
	}

	public synchronized void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public synchronized String getPath() {
		return path;
	}

	public synchronized void setPath(String path) {
		this.path = path;
	}

	public synchronized String getGroups() {
		return groups;
	}

	public synchronized void setGroups(String groups) {
		this.groups = groups;
	}
}
