package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Utils {

	private static DateFormat dateFormat = new SimpleDateFormat(
			"yyyyMMddhhmmss");
	private static int number = 0;

	public static synchronized int getNumber() {
		return ++number;
	}

	public static synchronized void resetNumber() {
		number = 0;
	}

	public static boolean hasEmpty(String... strs) {
		if (strs == null) {
			return true;
		}
		for (String str : strs) {
			if (str == null || str.trim().length() == 0) {
				return true;
			}
		}
		return false;
	}

	public static <T> T getFirst(List<T> list) {
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	public static String createOrderNumber() {

		String dateString = dateFormat.format(new Date());
		String number = getNumber() + "";

		if (number.length() > 7) {
			resetNumber();
		}
		for (int i = number.length(); i <= 7; i++) {
			number = "0" + number;
		}
		return "A" + dateString + number;
	}
}
