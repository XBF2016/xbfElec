package com.xbf.elec.service;

import java.util.List;
import java.util.Set;

import com.xbf.elec.dao.IElecUserDAO;
import com.xbf.elec.dao.util.Conditions;
import com.xbf.elec.dao.util.Conditions.Operator;
import com.xbf.elec.domain.ElecRole;
import com.xbf.elec.domain.ElecUser;
import com.xbf.elec.util.DataTablesPage;

public class ElecUserService {
	private IElecUserDAO userDAO;

	public synchronized IElecUserDAO getUserDAO() {
		return userDAO;
	}

	public synchronized void setUserDAO(IElecUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void add(ElecUser user) {
		userDAO.addOrUpdate(user);
	}

	public boolean checkAccountUnique(ElecUser user) {
		// 进行以account为条件的查询
		Conditions conditions = new Conditions();
		conditions.addCondition("account", user.getAccount(), Operator.EQUAL);
		List<ElecUser> list = userDAO.findByConditions(conditions);
		// 如果能查到说明不唯一
		if (list != null && list.size() > 0) {
			return false;
		}
		return true;
	}

	public void page(Conditions conditions, DataTablesPage<ElecUser> page) {
		userDAO.page(conditions, page);
	}

	public void delete(ElecUser user) {
		userDAO.delete(user);
	}

	public ElecUser findById(String account) {
		return userDAO.findById(account);
	}

	public void update(ElecUser user) {
		userDAO.addOrUpdate(user);
	}

	public ElecUser login(Conditions conditions) {
		List<ElecUser> list = userDAO.findByConditions(conditions);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public List<ElecUser> findByConditions(Conditions conditions) {
		return userDAO.findByConditions(conditions);
	}

	public void update(String userId, Set<ElecRole> roleSet) {
		ElecUser user = userDAO.findById(userId);
		user.setRoles(roleSet);
		userDAO.addOrUpdate(user);

	}

}
