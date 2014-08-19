package utils;

import android.annotation.SuppressLint;

public class StringUtils {
	
	@SuppressLint("DefaultLocale")
	public static boolean isEmpty(String str) {
		if (str != null) {
			if (str.length() > 4) {
				return false;
			}
		}
		return null == str || "".equals(str) || "NULL".equals(str.toUpperCase());
	}

	@SuppressLint("DefaultLocale")
	public static String maskNull(String str) {
		return isEmpty(str) || "NULL".equals(str.toUpperCase()) ? "" : str;
	}
	

}
