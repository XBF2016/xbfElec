package utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class SqlAndParamsFactory {

	public static <T> SqlAndParams forStatsCount(Class<T> clazz,
			Conditions conditions) {
		StringBuilder sql = new StringBuilder();
		Object[] params = null;

		try {
			sql.append("select count(*) from ").append(
					clazz.getDeclaredField("MAP_TABLE_NAME").get(null));

			Map<String, Object> conditionMap = conditions.getConditionMap();
			List<Boolean> needDimList = conditions.getNeedDimList();

			if (conditionMap != null && conditionMap.size() > 0) {
				sql.append(" where ");

				Set<Entry<String, Object>> entrySet = conditionMap.entrySet();
				int i = 0;
				// 因为有多少查询条件就会有多少?占位符
				params = new Object[entrySet.size()];

				for (Entry<String, Object> entry : entrySet) {
					String key = entry.getKey();
					Object value = entry.getValue();
					boolean needDim = needDimList.get(i);

					sql.append(key)
							.append(needDim ? " like ? and " : "=? and ");

					if (needDim) {
						params[i] = "%" + value + "%";
					} else {
						params[i] = value;
					}

					i++;
				}

				sql.delete(sql.length() - 4, sql.length());
			}

			return new SqlAndParams(sql.toString(), params);

		} catch (Exception e) {
			throw new RuntimeException("服务器错误 : " + e);
		}
	}

	public static <T> SqlAndParams forQuery(Class<T> clazz,
			Conditions conditions) {
		StringBuilder sql = new StringBuilder();
		Object[] params = null;

		try {
			sql.append("select * from ").append(
					clazz.getDeclaredField("MAP_TABLE_NAME").get(null));

			Map<String, Object> conditionMap = conditions.getConditionMap();
			List<Boolean> needDimList = conditions.getNeedDimList();

			if (conditionMap != null && conditionMap.size() > 0) {
				sql.append(" where ");

				Set<Entry<String, Object>> entrySet = conditionMap.entrySet();
				int i = 0;
				// 因为有多少查询条件就会有多少?占位符
				params = new Object[entrySet.size()];

				for (Entry<String, Object> entry : entrySet) {
					String key = entry.getKey();
					Object value = entry.getValue();
					boolean needDim = needDimList.get(i);

					sql.append(key)
							.append(needDim ? " like ? and " : "=? and ");

					if (needDim) {
						params[i] = "%" + value + "%";
					} else {
						params[i] = value;
					}

					i++;
				}

				sql.delete(sql.length() - 4, sql.length());
			}

			return new SqlAndParams(sql.toString(), params);
		} catch (Exception e) {
			throw new RuntimeException("服务器错误 : " + e);
		}
	}

	public static <T> SqlAndParams forDelete(T bean) {
		// delete from tableName where id=?

		StringBuilder sql = new StringBuilder();
		Object[] params = new Object[1];

		Class<T> clazz = (Class<T>) bean.getClass();

		try {
			sql.append("delete from ").append(
					clazz.getDeclaredField("MAP_TABLE_NAME").get(null));
			sql.append(" where id=?");

			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			PropertyDescriptor[] pDescriptors = beanInfo
					.getPropertyDescriptors();

			for (PropertyDescriptor propertyDescriptor : pDescriptors) {
				String fieldName = propertyDescriptor.getName();

				if ("id".equals(fieldName)) {
					Object idValue = propertyDescriptor.getReadMethod().invoke(
							bean);
					params[0] = idValue;
					break;
				}
			}

			return new SqlAndParams(sql.toString(), params);
		} catch (Exception e) {
			throw new RuntimeException("服务器错误 : " + e);
		}
	}

	public static <T> SqlAndParams forUpdate(T bean) {
		// update tableName set n1=?,n2=? ,n3=?, ... where id=?

		StringBuilder sql = new StringBuilder();
		Object[] params = null;

		Class<T> clazz = (Class<T>) bean.getClass();

		try {
			sql.append("update ").append(
					clazz.getDeclaredField("MAP_TABLE_NAME").get(null));
			sql.append(" set ");

			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			PropertyDescriptor[] pDescriptors = beanInfo
					.getPropertyDescriptors();

			params = new Object[pDescriptors.length - 1];

			int i = 0;
			Object idValue = null;
			for (PropertyDescriptor propertyDescriptor : pDescriptors) {
				String fieldName = propertyDescriptor.getName();
				if ("class".equals(fieldName)) {
					continue;
				}

				Object fieldValue = propertyDescriptor.getReadMethod().invoke(
						bean);
				if ("id".equals(fieldName)) {
					idValue = fieldValue;
					continue;
				}
				sql.append(fieldName).append("=?,");
				params[i] = fieldValue;
				i++;
			}

			sql.deleteCharAt(sql.length() - 1);
			sql.append(" where id=?");
			params[params.length - 1] = idValue;

			return new SqlAndParams(sql.toString(), params);
		} catch (Exception e) {
			throw new RuntimeException("服务器错误:" + e);
		}
	}

	public static <T> SqlAndParams forInsert(T bean) {
		// insert into tableName(n1 ,n2,n3,...) values(?,?,?,...)
		Class<T> clazz = (Class<T>) bean.getClass();

		StringBuilder sql = new StringBuilder();
		Object[] params = null;// 占位符对应的值

		sql.append("insert into ");

		try {
			String tableName = (String) clazz
					.getDeclaredField("MAP_TABLE_NAME").get(null);
			sql.append(tableName).append("(");

			// 使用Java内省获得所有的字段名称
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			PropertyDescriptor[] pDescriptors = beanInfo
					.getPropertyDescriptors();
			params = new Object[pDescriptors.length - 1];

			int i = 0;
			// 占位符字符串
			StringBuilder p = new StringBuilder();

			for (PropertyDescriptor propertyDescriptor : pDescriptors) {
				String fieldName = propertyDescriptor.getName();

				if ("class".equals(fieldName)) {
					continue;
				}

				Object fieldValue = propertyDescriptor.getReadMethod().invoke(
						bean);

				sql.append(fieldName).append(",");
				p.append("?,");
				params[i] = fieldValue;
				i++;

			}
			sql.deleteCharAt(sql.length() - 1);
			sql.append(") values(");
			p.deleteCharAt(p.length() - 1);
			sql.append(p).append(")");

			return new SqlAndParams(sql.toString(), params);
		} catch (Exception e) {
			throw new RuntimeException("服务器错误 : " + e);
		}
	}
}
