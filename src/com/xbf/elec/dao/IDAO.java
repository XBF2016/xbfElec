package com.xbf.elec.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.xbf.elec.dao.util.Conditions;
import com.xbf.elec.util.DataTablesPage;

//顶层DAO接口
public interface IDAO<T> {
	void addOrUpdate(T bean);

	void addOrUpdateAll(Collection<T> beans);

	void deleteById(Serializable id);

	void deleteAllByIds(Serializable... ids);

	void delete(T bean);

	void deleteAll(Collection<T> beans);

	T findById(Serializable id);

	List<T> findAll();

	List<T> findByConditions(Conditions conditions);

	void page(Conditions conditions, DataTablesPage<T> page);
}
