package database.utils;

import android.annotation.SuppressLint;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 字符串帮助类
 * 
 * @author LeeFranker
 * 
 */
public class StrUtils {

	/**
	 * 获取系统时间
	 * 
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getSystemTime() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result = format.format(date);
		return result;
	}

}
