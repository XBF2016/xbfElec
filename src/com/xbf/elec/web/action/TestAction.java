package com.xbf.elec.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.xbf.elec.domain.ElecTest;
import com.xbf.elec.service.ElecTestService;

public class TestAction extends ActionSupport implements ModelDriven<ElecTest> {
	private ElecTest elecTest = new ElecTest();
	private ElecTestService elecTestService;

	public String add() {
		elecTestService.add(elecTest);
		return "add";

	}

	public synchronized ElecTestService getElecTestService() {
		return elecTestService;
	}

	public synchronized void setElecTestService(ElecTestService elecTestService) {
		this.elecTestService = elecTestService;
	}

	public synchronized ElecTest getElecTest() {
		return elecTest;
	}

	public synchronized void setElecTest(ElecTest elecTest) {
		this.elecTest = elecTest;
	}

	public ElecTest getModel() {
		return elecTest;
	}

}
