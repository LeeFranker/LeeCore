package database.core;

import log.Log;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库辅助类
 * 
 * @author LeeFranker
 * 
 */
@SuppressWarnings("rawtypes")
public class SQLiteHelper<T> extends SQLiteOpenHelper implements SQLite {
	private static final String TAG = SQLiteHelper.class.getName();

	private static SQLiteHelper instance;

	public static SQLiteHelper getInstance() {
		return instance;
	}

	/**
	 * 初始化数据库
	 * 
	 * @param context
	 * @param dbName
	 * @param dbVersion
	 */
	public static void initiate(Context context, String dbName, int dbVersion) {
		instance = new SQLiteHelper(context, dbName, dbVersion);
	}

	/**
	 * SQLite系统维护的表，记录数据库相关属性信息。
	 */
	private static final String SYSTEM_TABLE = "sqlite_master";

	/**
	 * 系统表中记录数据表名称的字段名称。
	 */
	private static final String TABLE_NAME = "name";

	/**
	 * 初始化SQLiteHelper类
	 */
	private SQLiteHelper(Context context, String dbName, int dbVersion) {
		super(context, dbName, null, dbVersion);
	}

	/**
	 * 判断表是否存在
	 */
	@Override
	public boolean isTableExist(DBTable table) {
		SQLiteDatabase db = getWritableDatabase();
		// sqlite_master数据表是数据库维护的系统数据表
		Cursor cur = db.query(SYSTEM_TABLE, new String[] { TABLE_NAME },
				TABLE_NAME + " = " + "'" + table.getName() + "'", null, null,
				null, null);
		if (cur.moveToFirst()) {
			// 记得cursor使用之后一定要close
			cur.close();
			return true;
		} else {
			cur.close();
			return false;
		}
	}

	/**
	 * 如果数据表在数据库中不存在，则创建这个数据表
	 */
	public void createIfNotExist(DBTable table) {
		if (!isTableExist(table)) {
			createTable(table);
		}
	}

	/**
	 * 创建表
	 */
	@Override
	public void createTable(DBTable table) {
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL(table.getTableString());
	}

	/**
	 * 删除表
	 */
	@Override
	public void dropTable(DBTable table) {
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL("DROP TABLE IF EXISTS " + table.getName());
	}

	@Override
	public boolean isTableEmpty(DBTable table) {
		if (!isTableExist(table)) {
			return false;
		}
		SQLiteDatabase db = getWritableDatabase();
		Cursor cur = db.rawQuery("SELECT * FROM " + table.getName(), null);
		boolean result = (cur.getCount() == 0);
		cur.close();
		return result;
	}

	/**
	 * 创建表
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		createTables(db);
		Log.d(TAG, "完成创建所有数据表");
	}

	/**
	 * 创建表
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
		Log.d(TAG, "onUpgrade-新版本：" + newVer);
		Log.d(TAG, "onUpgrade-旧版本：" + oldVer);
		update(db, oldVer, newVer);
		Log.d(TAG, "完成onUpgrade");
	}

	/**
	 * 如果数据库版本降低删除所有表
	 */
	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVer, int newVer) {
		Log.d(TAG, "onDowngrade-新版本：" + newVer);
		Log.d(TAG, "onDowngrade-旧版本：" + oldVer);
		dropTables(db);
		Log.d(TAG, "完成onDowngrade");
	}

	/**
	 * 升级数据库，可以增加表，可以升级表（例如增加表的字段，或者减少表的字段）
	 * 
	 * @param db
	 * @param oldVer
	 * @param newVer
	 */
	private void update(SQLiteDatabase db, int oldVer, int newVer) {

	}

	/**
	 * 关闭数据库
	 */
	@Override
	public void closeDataBase() {
		SQLiteDatabase db = getWritableDatabase();
		db.close();
	}

	/**
	 * 创建所有表
	 */
	private void createTables(SQLiteDatabase db) {

	}

	/**
	 * 删除所有表
	 */
	private void dropTables(SQLiteDatabase db) {

	}

}
