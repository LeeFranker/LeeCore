package database.table;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import database.bean.HisBean;
import database.core.BaseDBTable;
import database.core.SQLiteHelper;
import database.utils.StrUtils;

/**
 * @ClassName: HisTable
 * @Description: TODO(测试类，历史表)
 * @author LeeFranker
 * @date 2014-2-15 下午5:08:40
 * 
 */
@SuppressWarnings("rawtypes")
public class HisTable extends BaseDBTable<HisBean> {
	private static final int TABLE_COUNT = 100;// 历史数据表最多存储个数
	private static final String DB_TABLE_HISTORY = "historytable";// 历史表名字

	// 定义表的字段
	private static final String KEY_HISTORY_ID = "historyId";// 影片Id
	private static final String KEY_HISTORY_NAME = "historyName";// 影片名字
	private static final String KEY_HISTORY_IMAGE = "historyMImage";// 影片图片地址
	private static final String KEY_HISTORY_DATETIME = "historyDatetime";// 影片播放日期

	@Override
	public String getName() {
		return DB_TABLE_HISTORY;
	}

	@Override
	public String getTableString() {
		return "CREATE TABLE IF NOT EXISTS " + DB_TABLE_HISTORY + " (" + KEY_ID
				+ " INTEGER PRIMARY KEY," + KEY_HISTORY_ID + " TEXT,"
				+ KEY_HISTORY_NAME + " TEXT," + KEY_HISTORY_IMAGE + " TEXT,"
				+ KEY_HISTORY_DATETIME + " DATETIME)";
	}

	@Override
	public ContentValues getContentValues(HisBean instance) {
		ContentValues initialValues = new ContentValues();
		if (instance.getId() != null)
			initialValues.put(KEY_HISTORY_ID, instance.getId());
		if (instance.getName() != null)
			initialValues.put(KEY_HISTORY_NAME, instance.getName());
		if (instance.getImageUrl() != null)
			initialValues.put(KEY_HISTORY_IMAGE, instance.getImageUrl());
		initialValues.put(KEY_HISTORY_DATETIME, StrUtils.getSystemTime());
		return initialValues;
	}

	@Override
	public HisBean getFilledInstance(Cursor cursor) {
		HisBean bean = new HisBean();
		String imageUrl = cursor.getString(cursor
				.getColumnIndex(KEY_HISTORY_IMAGE));
		bean.setImageUrl(imageUrl);
		String id = cursor.getString(cursor.getColumnIndex(KEY_HISTORY_ID));
		bean.setId(id);
		String name = cursor.getString(cursor.getColumnIndex(KEY_HISTORY_NAME));
		bean.setName(name);
		return bean;
	}

	@Override
	public void initialeColumes() {
		// TODO 初始化字段

	}

	@Override
	public ArrayList<HisBean> fetchTableAllData() {
		ArrayList<HisBean> dataList = new ArrayList<HisBean>();
		SQLiteHelper helper = SQLiteHelper.getInstance();
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor cursor = db.query(DB_TABLE_HISTORY, new String[] { KEY_ID,
				KEY_HISTORY_ID, KEY_HISTORY_NAME }, null, null, null, null,
				KEY_HISTORY_DATETIME + " desc");
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				while (!cursor.isAfterLast()) {
					HisBean bean = getFilledInstance(cursor);
					dataList.add(bean);
					cursor.moveToNext();
				}
			}
			cursor.close();
		}
		return dataList;
	}

	@Override
	public long fetchEarlyRowIdByDataTime() {
		SQLiteHelper helper = SQLiteHelper.getInstance();
		SQLiteDatabase db = helper.getWritableDatabase();
		int rowId = 0;
		Cursor mCursor = db.query(true, DB_TABLE_HISTORY, new String[] {
				KEY_ID, KEY_HISTORY_DATETIME }, null, null, null, null, null,
				null);
		if (mCursor != null) {
			if (mCursor.moveToFirst()) {
				rowId = mCursor.getInt(mCursor.getColumnIndexOrThrow(KEY_ID));
			}
			mCursor.close();
		}
		return rowId;
	}

	@Override
	public void updateTable(HisBean instance) {
		SQLiteHelper helper = SQLiteHelper.getInstance();
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues cv = getContentValues(instance);
		db.update(DB_TABLE_HISTORY, cv, KEY_HISTORY_ID + "=?",
				new String[] { instance.getId() });

	}

	/**
	 * 根据id删除历史记录
	 * 
	 * @param id
	 */
	public void deleteHistoryDataByTvId(HisBean instance) {
		SQLiteHelper helper = SQLiteHelper.getInstance();
		SQLiteDatabase db = helper.getWritableDatabase();
		db.delete(DB_TABLE_HISTORY, KEY_HISTORY_ID + "=?",
				new String[] { instance.getId() });
	}

	/**
	 * 根据id删除历史记录
	 * 
	 * @param id
	 */
	public void deleteHistoryDataByTvId(String id) {
		SQLiteHelper helper = SQLiteHelper.getInstance();
		SQLiteDatabase db = helper.getWritableDatabase();
		db.delete(DB_TABLE_HISTORY, KEY_HISTORY_ID + "=?", new String[] { id });
	}

	/**
	 * 根据id获取历史记录
	 * 
	 * @param tvId
	 * @return
	 */
	public HisBean fetchHistoryDataByTvId(HisBean instance) {
		SQLiteHelper helper = SQLiteHelper.getInstance();
		SQLiteDatabase db = helper.getWritableDatabase();
		HisBean bean = null;
		Cursor cursor = db.query(true, DB_TABLE_HISTORY, new String[] { KEY_ID,
				KEY_HISTORY_ID, KEY_HISTORY_IMAGE, KEY_HISTORY_NAME },
				KEY_HISTORY_ID + "=?", new String[] { instance.getId() }, null,
				null, KEY_HISTORY_DATETIME + " desc", null);
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				bean = getFilledInstance(cursor);
			}
			cursor.close();
		}
		return bean;
	}

	/**
	 * 根据id获取历史记录
	 * 
	 * @param tvId
	 * @return
	 */
	public HisBean fetchHistoryDataByTvId(String id) {
		SQLiteHelper helper = SQLiteHelper.getInstance();
		SQLiteDatabase db = helper.getWritableDatabase();
		HisBean bean = null;
		Cursor cursor = db.query(true, DB_TABLE_HISTORY, new String[] { KEY_ID,
				KEY_HISTORY_ID, KEY_HISTORY_IMAGE, KEY_HISTORY_NAME },
				KEY_HISTORY_ID + "=?", new String[] { id }, null, null,
				KEY_HISTORY_DATETIME + " desc", null);
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				bean = getFilledInstance(cursor);
			}
			cursor.close();
		}
		return bean;
	}

	/**
	 * 根据id判断这条记录是否存在
	 * 
	 * @param id
	 * @return
	 */
	public boolean isExistHistoryData(HisBean instance) {
		SQLiteHelper helper = SQLiteHelper.getInstance();
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor cursor = db.query(DB_TABLE_HISTORY,
				new String[] { KEY_HISTORY_ID }, KEY_HISTORY_ID + "=?",
				new String[] { instance.getId() }, null, null, null);
		if (cursor != null) {
			boolean result = cursor.moveToFirst();
			cursor.close();
			return result;
		}
		return false;
	}

	/**
	 * 根据id判断这条记录是否存在
	 * 
	 * @param id
	 * @return
	 */
	public boolean isExistHistoryData(String id) {
		SQLiteHelper helper = SQLiteHelper.getInstance();
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor cursor = db.query(DB_TABLE_HISTORY,
				new String[] { KEY_HISTORY_ID }, KEY_HISTORY_ID + "=?",
				new String[] { id }, null, null, null);
		if (cursor != null) {
			boolean result = cursor.moveToFirst();
			cursor.close();
			return result;
		}
		return false;
	}

	/**
	 * 综合插入方法，又插入个数限制
	 * 
	 * @param bean
	 */
	public void insertHistory2Database(HisBean bean) {
		long count = fetchTableAllSize();
		if (count >= 0 && count < TABLE_COUNT) {
			if (!isExistHistoryData(bean)) {
				insertTable(bean);
			} else {
				updateTable(bean);
			}
		} else if (count >= TABLE_COUNT) {
			long rowId = fetchEarlyRowIdByDataTime();
			deleteTableByRowId(rowId);
			insertTable(bean);
		}
	}

}
