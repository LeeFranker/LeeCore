package database.core;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * 表接口定义
 * 
 * @author LeeFranker
 * 
 * @param <T>
 */
public interface DBTable<T> {

	/**
	 * 返回数据表的表名
	 */
	public String getName();

	/**
	 * 添加字段
	 */
	public void addColumn(String colName, SQLiteDataType type,
			SQLiteConstraint constraint);

	/**
	 * 返回创建的SQLite语句
	 */
	public String createTableString();

	/**
	 * 返回固定的SQLite语句
	 */
	public String getTableString();

	/**
	 * 返回表中的字段名称
	 */
	public String[] getColums();

	/**
	 * 根据丢过来的实例，返回包含属性内容的ContentValues
	 */
	public ContentValues getContentValues(T instance);

	/**
	 * 从cursor中获取数据，填充一个实例，然后返回这个实例
	 */
	public T getFilledInstance(Cursor cursor);

	/**
	 * 插入数据
	 * 
	 * @param type
	 * @param object
	 */
	public void insertTable(T instance);

	/**
	 * 根据行号删除数据
	 * 
	 * @param type
	 * @param rowId
	 */
	public void deleteTableByRowId(long rowId);

	/**
	 * 清空数据
	 * 
	 * @param type
	 */
	public void cleanTable();

	/**
	 * 获取所有数据
	 * 
	 * @param type
	 * @return
	 */
	public ArrayList<T> fetchTableAllData();

	/**
	 * 获取所有数据个数
	 * 
	 * @param type
	 * @return
	 */
	public long fetchTableAllSize();

	/**
	 * 获取数据中最早的行号
	 * 
	 * @param type
	 * @return
	 */
	public long fetchEarlyRowIdByDataTime();

	/**
	 * 更新数据
	 * 
	 * @param type
	 * @param object
	 */
	public void updateTable(T instance);

}
