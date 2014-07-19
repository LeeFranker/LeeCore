package database.core;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import database.bean.Entity;

/**
 * 实现了DBtable数据表接口，自定义数据表
 * 
 * @author LeeFranker
 * 
 * @param <T>
 */
@SuppressWarnings("rawtypes")
public abstract class BaseDBTable<T extends Entity> implements DBTable<T> {

	/**
	 * 主键
	 */
	public static final String KEY_ID = "_id";

	public BaseDBTable() {
		initialeColumes();
	}

	/**
	 * 数据表字段集合
	 */
	private ArrayList<String> columns = new ArrayList<String>();

	/**
	 * 数据表字段对应的类型
	 */
	private ArrayList<SQLiteDataType> types = new ArrayList<SQLiteDataType>();

	/**
	 * 每个字段对应的约束
	 */
	private HashMap<String, SQLiteConstraint> constraints = new HashMap<String, SQLiteConstraint>();

	/**
	 * 初始化表的字段
	 */
	public abstract void initialeColumes();

	@SuppressLint("DefaultLocale")
	@Override
	public synchronized String createTableString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("CREATE TABLE ").append(getName()).append(" ")
				.append("(_id " + SQLiteDataType.INTEGER + " PRIMARY KEY, ");
		for (int i = 0; i < columns.size() - 1; i++) {
			buffer.append(columns.get(i)).append(" ").append(types.get(i))
					.append(" ");
			SQLiteConstraint constraint = constraints.get(columns.get(i));
			if (constraint != null) {
				buffer.append(constraint.toString().toUpperCase());
			}
			buffer.append(", ");
		}
		int last = columns.size() - 1;
		buffer.append(columns.get(last)).append(" ").append(types.get(last))
				.append(" ");
		SQLiteConstraint constraint = constraints.get(columns.get(last));
		if (constraint != null) {
			buffer.append(constraint.toString().toUpperCase());
		}
		buffer.append(");");
		return buffer.toString();
	}

	@Override
	public synchronized void addColumn(String colName, SQLiteDataType type,
			SQLiteConstraint constraint) {
		if (colName == null || type == null) {
			throw new NullPointerException(
					"Column name or type can not be null.");
		}
		columns.add(colName);
		types.add(type);
		if (constraint != null) {
			constraints.put(colName, constraint);
		}
	}

	@Override
	public synchronized String[] getColums() {
		String[] cols = new String[columns.size()];
		return columns.toArray(cols);
	}

	@Override
	public void insertTable(T instance) {
		SQLiteHelper helper = SQLiteHelper.getInstance();
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues contentValues = getContentValues(instance);
		db.insert(getName(), KEY_ID, contentValues);
	}

	@Override
	public void deleteTableByRowId(long rowId) {
		SQLiteHelper helper = SQLiteHelper.getInstance();
		SQLiteDatabase db = helper.getWritableDatabase();
		db.delete(getName(), KEY_ID + "=" + rowId, null);
	}

	@Override
	public void cleanTable() {
		SQLiteHelper helper = SQLiteHelper.getInstance();
		SQLiteDatabase db = helper.getWritableDatabase();
		db.delete(getName(), null, null);
	}

	@Override
	public long fetchTableAllSize() {
		long size = 0;
		SQLiteHelper helper = SQLiteHelper.getInstance();
		SQLiteDatabase database = helper.getReadableDatabase();
		Cursor cursor = database.rawQuery("select count(*) from " + getName(),
				null);
		if (cursor != null) {
			cursor.moveToFirst();
			size = cursor.getLong(0);
			cursor.close();
		}
		return size;
	}

}
