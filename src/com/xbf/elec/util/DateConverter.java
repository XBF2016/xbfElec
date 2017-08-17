package com.xbf.elec.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;

//自定义的struts2日期转换类：解决struts2标签访问web域中的Date对象在浏览器中的显示问题
public class DateConverter extends DefaultTypeConverter {
	// 定义两种日期显示格式
	private SimpleDateFormat[] simpleDateFormats = {
			new SimpleDateFormat("yyyy-MM-dd"),
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") };

	@Override
	public Object convertValue(Map<String, Object> context, Object value,
			Class toType) {

		// String 转 Date
		if (toType == Date.class) {
			String[] strings = (String[]) value;
			String dateStr = strings[0];
			Date date = null;
			// String的信息有可能不够多，少转多会报错
			// 如果字符串不符合格式抛出异常，但是不处理，进行下一次循环，知道符合格式就转换为Date类型
			for (SimpleDateFormat simpleDateFormat : simpleDateFormats) {
				try {
					date = simpleDateFormat.parse(dateStr);
				} catch (Exception e) {
				}
			}
			// 如果最终都没有符合的就抛出异常
			if (date == null) {
				throw new RuntimeException("Could not parse date : " + dateStr);
			}
			return date;
		}

		// Date转String
		else if (toType == String.class) {
			Date date = (Date) value;
			// Date里面的信息是全的，全的转换为少的不会抛出异常
			return simpleDateFormats[1].format(date);
		}

		return null;
	}
}
