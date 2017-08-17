package com.xbf.elec.dao.impl;

//顶层DAO实现类
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.xbf.elec.dao.IDAO;
import com.xbf.elec.dao.util.Conditions;
import com.xbf.elec.dao.util.Conditions.WhereAndValue;
import com.xbf.elec.util.DataTablesPage;

public class BaseDAO<T> implements IDAO<T> {
	private HibernateTemplate hibernateTemplate;
	private Class beanClass;

	// 获取参数类型
	{
		ParameterizedType paramType = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		Type[] argTypes = paramType.getActualTypeArguments();
		beanClass = (Class) argTypes[0];
	}

	public T findById(Serializable id) {
		return (T) hibernateTemplate.get(beanClass, id);
	}

	public List<T> findByConditions(Conditions conditions) {
		WhereAndValue whereAndValue = conditions.createWhereAndValue();
		System.out.println(whereAndValue.getWhere());
		String hql = " from " + beanClass.getName() + whereAndValue.getWhere()
				+ conditions.createOrderBy();
		Object[] values = whereAndValue.getValues();
		return hibernateTemplate.find(hql, values);

	}

	public synchronized HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public synchronized void setHibernateTemplate(
			HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void addOrUpdate(T bean) {
		hibernateTemplate.saveOrUpdate(bean);
	}

	public void addOrUpdateAll(Collection<T> beans) {
		if (beans != null) {
			hibernateTemplate.saveOrUpdateAll(beans);
		}
	}

	public void delete(T bean) {
		hibernateTemplate.delete(bean);
	}

	public void deleteAll(Collection<T> beans) {
		if (beans != null) {
			hibernateTemplate.deleteAll(beans);
		}
	}

	public void deleteAllByIds(Serializable... ids) {
		if (ids != null) {
			for (Serializable id : ids) {
				deleteById(id);
			}
		}

	}

	public void deleteById(Serializable id) {
		T bean = findById(id);
		if (bean != null) {
			delete(bean);
		}

	}

	public List<T> findAll() {
		String hql = " from " + beanClass.getName();
		return hibernateTemplate.find(hql);
		// return hibernateTemplate.loadAll(beanClass);
	}

	// 分页条件查询
	public void page(Conditions conditions, final DataTablesPage<T> page) {
		WhereAndValue wv = conditions.createWhereAndValue();
		final String hql = " from " + beanClass.getName() + wv.getWhere();
		final Object[] objects = wv.getValues();
		List<T> data = hibernateTemplate
				.execute(new HibernateCallback<List<T>>() {

					public List<T> doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						// 设置占位符的值
						if (objects != null && objects.length > 0) {
							for (int i = 0; i < objects.length; i++) {
								query.setParameter(i, objects[i]);
							}
						}
						// 设置查询的起点和数量
						query.setFirstResult(page.getIDisplayStart());
						query.setMaxResults(page.getIDisplayLength());
						return query.list();
					}
				});
		page.setData(data);

		final String totalHql = "select count(*)  " + hql;
		long iTotalRecords = hibernateTemplate
				.execute(new HibernateCallback<Long>() {

					public Long doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(totalHql);
						// 设置占位符的值
						if (objects != null && objects.length > 0) {
							for (int i = 0; i < objects.length; i++) {
								query.setParameter(i, objects[i]);
							}
						}
						// 拿到第一个结果
						return (Long) query.uniqueResult();
					}
				});
		page.setITotalDisplayRecords((int) iTotalRecords);
		page.setITotalRecords((int) iTotalRecords);
	}
}
