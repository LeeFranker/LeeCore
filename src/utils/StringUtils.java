package utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

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

	public static boolean isEmptyArray(Object[] array) {
		return isEmptyArray(array, 1);
	}

	public static boolean isEmptyArray(Object array) {
		return null == array;
	}

	public static boolean isEmptyArray(Object[] array, int len) {
		return null == array || array.length < len;
	}

	public static String getStrFromInputstream(InputStream input) {
		String result = null;
		int i = -1;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			while ((i = input.read()) != -1) {
				baos.write(i);
			}
			result = baos.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getStrFromInput(InputStream input) {
		int i = -1;
		String result = null;
		byte[] b = new byte[1024];
		StringBuffer sb = new StringBuffer();
		try {
			while ((i = input.read(b)) != -1) {
				sb.append(new String(b, 0, i));
			}
			result = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
