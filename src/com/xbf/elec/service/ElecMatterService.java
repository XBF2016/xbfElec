package com.xbf.elec.service;

import java.util.List;

import com.xbf.elec.dao.IElecMatterDAO;
import com.xbf.elec.domain.ELecMatter;

public class ElecMatterService {
	private IElecMatterDAO elecMatterDAO;

	public synchronized IElecMatterDAO getElecMatterDAO() {
		return elecMatterDAO;
	}

	public synchronized void setElecMatterDAO(IElecMatterDAO elecMatterDAO) {
		this.elecMatterDAO = elecMatterDAO;
	}

	// 先删除再查询
	public void addAfterDelete(ELecMatter matter) {
		// 删除旧数据
		// 先查询再删除
		List<ELecMatter> matters = elecMatterDAO.findAll();
		if (matters != null) {
			elecMatterDAO.deleteAll(matters);
		}
		// 添加
		elecMatterDAO.addOrUpdate(matter);

	}

	public ELecMatter findOne() {
		List<ELecMatter> matters = elecMatterDAO.findAll();
		// 有数据才返回
		if (matters != null && matters.size() != 0) {
			return matters.get(0);
		}
		return null;
	}

	// 查询事宜

}
