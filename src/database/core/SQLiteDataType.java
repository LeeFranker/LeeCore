package database.core;

/**
 * SQLite的基本5种类型
 * 
 * @author LeeFranker
 * 
 */
public enum SQLiteDataType {

	/**
	 * 字段的类型为空
	 */
	NULL,
	/**
	 * 字段的类型为整形
	 */
	INTEGER,
	/**
	 * 字段的类型为字符串
	 */
	TEXT,
	/**
	 * 字段的类型为浮点型（已8字节存储）
	 */
	REAL,
	/**
	 * 字段的类型为长整形
	 */
	LONG,
	/**
	 * 字段的类型为二进制
	 */
	BOLB;

}
