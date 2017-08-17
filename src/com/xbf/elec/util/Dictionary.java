package com.xbf.elec.util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

//单例模式
public class Dictionary {

	// 单例模式的对象：私有且静态
	private static Dictionary instance;

	// 相关的数据结构：当init方法调用后会将数据存放在它们里面，访问时应该先访问本对象再访问数据结构
	// 1.给下拉框用的数据结构 <group,<itemkey,itemvalue>>
	Map<String, Map<String, String>> dictMap;
	// 辅助Map，存放group的key和value <groupkey,groupvalue>
	Map<String, String> groupMap;
	// 2.没有group分类的数据结构，<itemkey,itemvalue>
	Map<String, String> itemMap;

	// 私有的构造函数，只能内部实例化对象
	private Dictionary() {

	}

	// 静态的方法，外界通过它拿到对象
	// 为了防止其他进程调用此方法破坏单例，加上同步锁
	public static synchronized Dictionary getInstance() {
		// 如果还没有初始化就初始化
		if (instance == null) {
			instance = new Dictionary();
			instance.init();
		}

		return instance;
	}

	// 初始化函数，用于读取字典数据
	private void init() {
		// 三个数据结构先不要直接赋值，而是间接地
		// 1.给下拉框用的数据结构 <group,<itemkey,itemvalue>>
		Map<String, Map<String, String>> dictMap2 = new LinkedHashMap<String, Map<String, String>>();
		// 辅助Map，存放group的key和value <groupkey,groupvalue>
		Map<String, String> groupMap2 = new LinkedHashMap<String, String>();
		// 2.没有group分类的数据结构，<itemkey,itemvalue>
		Map<String, String> itemMap2 = new LinkedHashMap<String, String>();

		Document document = null;

		// 解析XML文件
		// 先使用类解析器拿到URL，再拿到绝对路径
		String filePath = this.getClass().getResource("/dictionary.xml")
				.getFile();
		SAXReader saxReader = new SAXReader();
		try {
			document = saxReader.read(filePath);
			// 拿到根节点
			Element rootElement = document.getRootElement();

			// 拿到所有的group节点
			List<Element> groupElements = rootElement.elements();
			for (Element groupElement : groupElements) {
				String groupKey = groupElement.attributeValue("key");
				String groupValue = groupElement.attributeValue("value");
				// 数据的有效性检查
				if (groupKey == null || groupKey.trim().length() == 0) {
					throw new RuntimeException("数据字典的数据无效");
				}
				if (groupValue == null || groupValue.trim().length() == 0) {
					throw new RuntimeException("数据字典的数据无效");
				}
				groupMap2.put(groupKey, groupValue);

				// 对每一个根节点再访问其所有子节点item
				// 拿到所有的item节点
				Map<String, String> groupItemMap = new LinkedHashMap<String, String>();// 存放某个group下的所有items
				List<Element> itemElements = groupElement.elements();
				for (Element itemElement : itemElements) {
					String itemKey = itemElement.attributeValue("key");
					String itemValue = itemElement.attributeValue("value");
					// 数据的有效性检查
					if (itemKey == null || itemKey.trim().length() == 0) {
						throw new RuntimeException("数据字典的数据无效");
					}
					if (itemValue == null || itemValue.trim().length() == 0) {
						throw new RuntimeException("数据字典的数据无效");
					}
					groupItemMap.put(itemKey, itemValue);
					itemMap2.put(itemKey, itemValue);
				}
				// 处理完一个group就要添加到dictMap2
				dictMap2.put(groupKey, groupItemMap);
			}
		} catch (DocumentException e) {
			throw new RuntimeException("初始化数据字典失败：" + e);
		}
		// 全部完成才能赋值
		dictMap = dictMap2;
		groupMap = groupMap2;
		itemMap = itemMap2;
	}

	// 重载函数，外界通过它重新加载字典数据
	// 为了防止其他进程调用此方法破坏单例，加上同步锁
	public static synchronized void reload() {
		// 如果还没创建
		if (instance == null) {
			getInstance();
		} else {
			instance.init();
		}
	}

	public synchronized Map<String, Map<String, String>> getDictMap() {
		return dictMap;
	}

	public synchronized void setDictMap(Map<String, Map<String, String>> dictMap) {
		this.dictMap = dictMap;
	}

	public synchronized Map<String, String> getGroupMap() {
		return groupMap;
	}

	public synchronized void setGroupMap(Map<String, String> groupMap) {
		this.groupMap = groupMap;
	}

	public synchronized Map<String, String> getItemMap() {
		return itemMap;
	}

	public synchronized void setItemMap(Map<String, String> itemMap) {
		this.itemMap = itemMap;
	}

}
