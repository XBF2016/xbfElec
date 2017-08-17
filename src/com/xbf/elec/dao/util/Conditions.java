package com.xbf.elec.dao.util;

import java.util.ArrayList;
import java.util.List;

//高内聚类：所有与条件查询有关的方法和类都在里面
public class Conditions {
	// 条件仓库
	private List<Condition> conditions = new ArrayList<Condition>();

	// 排序条件仓库
	private List<OrderBy> orderBys = new ArrayList<OrderBy>();

	// 操作数枚举
	public enum Operator {
		EQUAL, LIKE, NOT_EQUAL, GREATER, GREATER_EQUAL, LOWER, LOWER_EQUAL, IS, NOT_IS;
	}

	// 条件类
	class Condition {
		// 条件的三部分，key，value，operator
		private String key = "";
		private Object value;
		private Operator operator;

		public synchronized String getKey() {
			return key;
		}

		public synchronized void setKey(String key) {
			this.key = key;
		}

		public synchronized Object getValue() {
			return value;
		}

		public synchronized void setValue(Object value) {
			this.value = value;
		}

		public synchronized Operator getOperator() {
			return operator;
		}

		public synchronized void setOperator(Operator operator) {
			this.operator = operator;
		}

		public Condition(String key, Object value, Operator operator) {
			super();
			this.key = key;
			this.value = value;
			this.operator = operator;
		}

		public Condition() {
			super();
		}

	}

	// 添加条件的类:严格：不合格的数据不添加；安静：不抛出异常
	public void addCondition(String key, Object value, Operator operator) {
		// 数据检验
		if (key == null || key.trim().length() == 0) {
			return;
		}
		if (value == null) {
			if (operator != Operator.IS || operator != Operator.NOT_IS) {
				return;
			}
		}
		if (value != null && value instanceof String) {
			if (((String) value).trim().length() == 0) {
				return;
			}
		}
		if (operator == null) {
			return;
		}

		// 添加条件到List中
		Condition condition = new Condition(key, value, operator);
		conditions.add(condition);
	}

	// 根据List中的条件转换为hql语句和占位符值数组
	public class WhereAndValue {
		private String where;
		private Object[] values;

		public WhereAndValue() {
			super();
		}

		public synchronized String getWhere() {
			return where;
		}

		public synchronized void setWhere(String where) {
			this.where = where;
		}

		public synchronized Object[] getValues() {
			return values;
		}

		public synchronized void setValues(Object[] values) {
			this.values = values;
		}

	}

	// 转换
	public WhereAndValue createWhereAndValue() {
		WhereAndValue wv = new WhereAndValue();
		StringBuilder where = new StringBuilder(" where ");
		List<Object> values = new ArrayList<Object>();
		// 遍历List
		for (Condition condition : conditions) {
			String key = condition.getKey();
			Object value = condition.getValue();
			Operator operator = condition.getOperator();
			switch (operator) {
			case LIKE:
				// key like %xxx%
				where.append(key).append(" like ").append(" ? ");
				values.add("%" + value + "%");
				break;
			case EQUAL:
				where.append(key).append(" = ").append(" ? ");
				values.add(value);
				break;
			case NOT_EQUAL:
				where.append(key).append(" != ").append(" ? ");
				values.add(value);
				break;
			case GREATER:
				where.append(key).append(" > ").append(" ? ");
				values.add(value);
				break;
			case GREATER_EQUAL:
				where.append(key).append(" >= ").append(" ? ");
				values.add(value);
				break;
			case LOWER:
				where.append(key).append(" < ").append(" ? ");
				values.add(value);
				break;
			case LOWER_EQUAL:
				where.append(key).append(" <= ").append(" ? ");
				values.add(value);
				break;
			case IS:
				where.append(key).append(" is ").append(" ? ");
				values.add(null);
				break;
			case NOT_IS:
				where.append(key).append(" not is ").append(" ? ");
				values.add(null);
				break;
			}
			where.append("  and ");
		}
		String whereString = where.substring(0, where.length() - 6);
		wv.setWhere(whereString);
		wv.setValues(values.toArray());
		return wv;
	}

	// 排序条件
	class OrderBy {
		private String key;
		private boolean isAsc;

		public synchronized String getKey() {
			return key;
		}

		public synchronized void setKey(String key) {
			this.key = key;
		}

		public synchronized boolean isAsc() {
			return isAsc;
		}

		public synchronized void setAsc(boolean isAsc) {
			this.isAsc = isAsc;
		}

		public OrderBy(String key, boolean isAsc) {
			super();
			this.key = key;
			this.isAsc = isAsc;
		}

		public OrderBy() {
			super();
		}

	}

	// 添加排序条件的方法，严格且沉默
	public void addOrderBy(String key, boolean isAsc) {
		if (key == null || key.trim().length() == 0) {
			return;
		}
		OrderBy orderBy = new OrderBy(key, isAsc);
		orderBys.add(orderBy);
	}

	// 拿到排序语句
	public String createOrderBy() {
		if (orderBys.size() == 0) {
			return "";
		}
		StringBuilder orderByString = new StringBuilder(" order by ");
		for (OrderBy orderBy : orderBys) {
			orderByString.append(orderBy.getKey()).append(
					orderBy.isAsc ? " asc " : " desc ").append(" , ");
		}

		return orderByString.substring(0, orderByString.length() - 2);
	}
}
