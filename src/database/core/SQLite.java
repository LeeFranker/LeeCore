package database.core;


/**
 * 对表的操作
 * 
 * @author LeeFranker
 * 
 */
public interface SQLite<T> {

	/**
	 * 判断表是否存在
	 * 
	 * @param table
	 *            表对象
	 * @return true存在 false不存在
	 */
	public boolean isTableExist(DBTable<T> table);

	/**
	 * 数据表是否为空
	 * 
	 * @param table
	 * @return
	 */
	public boolean isTableEmpty(DBTable<T> table);

	/**
	 * 创建表
	 * 
	 * @param table
	 */
	public void createTable(DBTable<T> table);

	/**
	 * 删除表
	 * 
	 * @param table
	 */
	public void dropTable(DBTable<T> table);

	/**
	 * 关闭数据库
	 * 
	 */
	public void closeDataBase();

}
