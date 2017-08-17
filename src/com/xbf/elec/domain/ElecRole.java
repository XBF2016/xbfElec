package com.xbf.elec.domain;

import java.util.Set;

/*
 #角色表
 create table elecRole(
 roleId varchar(100) primary key,
 roleName varchar(50) unique
 );
 */
public class ElecRole {
	private String roleId;
	private String roleName;
	// 多端：权限
	private Set<ElecFunction> functions;

	public synchronized Set<ElecFunction> getFunctions() {
		return functions;
	}

	public synchronized void setFunctions(Set<ElecFunction> functions) {
		this.functions = functions;
	}

	public synchronized String getRoleId() {
		return roleId;
	}

	public synchronized void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public synchronized String getRoleName() {
		return roleName;
	}

	public synchronized void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
