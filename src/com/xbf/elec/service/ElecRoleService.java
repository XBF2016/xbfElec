package com.xbf.elec.service;

import java.util.List;
import java.util.Set;

import com.xbf.elec.dao.IElecRoleDAO;
import com.xbf.elec.domain.ElecFunction;
import com.xbf.elec.domain.ElecRole;

public class ElecRoleService {
	private IElecRoleDAO roleDAO;

	public synchronized IElecRoleDAO getRoleDAO() {
		return roleDAO;
	}

	public synchronized void setRoleDAO(IElecRoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public List<ElecRole> list() {
		return roleDAO.findAll();
	}

	public ElecRole findFunctionsByRoleId(String roleId) {
		return roleDAO.findById(roleId);
	}

	public void update(String roleId, Set<ElecFunction> functionSet) {
		// 查出来
		ElecRole role = roleDAO.findById(roleId);
		role.setFunctions(functionSet);
		roleDAO.addOrUpdate(role);
	}
}
