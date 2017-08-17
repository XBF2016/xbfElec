package com.xbf.elec.service;

import java.util.List;

import com.xbf.elec.dao.IElecFunctionDAO;
import com.xbf.elec.domain.ElecFunction;

public class ElecFunctionService {
	private IElecFunctionDAO functionDAO;

	public synchronized IElecFunctionDAO getFunctionDAO() {
		return functionDAO;
	}

	public synchronized void setFunctionDAO(IElecFunctionDAO functionDAO) {
		this.functionDAO = functionDAO;
	}

	public List<ElecFunction> list() {
		return functionDAO.findAll();
	}
}
