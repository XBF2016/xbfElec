package com.xbf.elec.service;

import java.io.Serializable;

import com.xbf.elec.dao.IElecTestDAO;
import com.xbf.elec.domain.ElecTest;

public class ElecTestService {
	private IElecTestDAO elecTestDAO;

	public void add(ElecTest elecTest) {
		elecTestDAO.addOrUpdate(elecTest);
	}

	public ElecTest findById(Serializable id) {
		return this.elecTestDAO.findById(id);
	}

	public synchronized IElecTestDAO getElecTestDAO() {
		return elecTestDAO;
	}

	public synchronized void setElecTestDAO(IElecTestDAO elecTestDAO) {
		this.elecTestDAO = elecTestDAO;
	}

}
