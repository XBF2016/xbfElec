package utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

//代表一个查询所有的条件   key  =  value    ( =   like)   
public class Conditions {

	// 1 为什么使用LinkedHashMap而不是用HashMap???????????
	private Map<String, Object> conditionMap = new LinkedHashMap<String, Object>();// ?
	private List<Boolean> needDimList = new ArrayList<Boolean>();

	public String createURLQueryString() {
		StringBuilder re = new StringBuilder();
		Set<Entry<String, Object>> entrySet = conditionMap.entrySet();
		for (Entry<String, Object> entry : entrySet) {
			re.append(entry.getKey()).append("=").append(entry.getValue())
					.append("&");
		}
		if (re.length() > 0) {
			re.deleteCharAt(re.length() - 1);
		}
		return re.toString();
	}

	public Map<String, Object> getConditionMap() {
		return conditionMap;
	}

	public List<Boolean> getNeedDimList() {
		return needDimList;
	}

	public boolean addContition(String key, Object value, boolean needDim) {
		// 条件的有效性检查
		if (key == null || key.trim().length() == 0) {
			return false;
		}
		if (value == null) {
			return false;
		}

		if (value instanceof String) {
			String str = (String) value;
			if (str.trim().length() == 0) {
				return false;
			}
		}

		conditionMap.put(key, value);
		needDimList.add(needDim);

		return true;

	}

	// 
	// Map<String, Object> conditionMap2 = new HashMap<String, Object>();
	// Map<String, Boolean> needDim2 = new HashMap<String, Boolean>();
}
