package database;

import android.content.Context;
import database.core.SQLiteHelper;

/**
 * 
 * 数据库管理类
 * 
 * @author LeeFranker
 * 
 */
@SuppressWarnings("rawtypes")
public class DataBaseMgr {

	/**
	 * 数据库版本号
	 */
	private static final int DB_VERSION = 1;
	/**
	 * 数据库名字
	 */
	private static final String DB_NAME = "database.db";

	/**
	 * 在application中调用
	 * 
	 * @param context
	 */

	public static void initDataBase(Context context) {
		SQLiteHelper.initiate(context, DB_NAME, DB_VERSION);
	}

	/**
	 * 关闭数据库，在退出程序的时候调用
	 */
	public static void closeDataBase() {
		SQLiteHelper helper = SQLiteHelper.getInstance();
		helper.closeDataBase();
	}

}
