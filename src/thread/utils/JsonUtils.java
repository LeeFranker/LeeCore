package thread.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import utils.StringUtils;
import android.text.TextUtils;

/**
 * json解析工具类
 * 
 * @author LeeFranker
 * 
 */
public class JsonUtils {
	public static boolean readBoolean(JSONObject obj, String key, boolean _defaultValue) {

		boolean rtnStr = _defaultValue;

		if (null == obj || TextUtils.isEmpty(key))
			return rtnStr;

		try {

			if (obj.has(key))
				rtnStr = obj.optBoolean(key, _defaultValue);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			obj = null;
			key = null;
		}
		return rtnStr;

	}

	public static JSONArray readArr(JSONObject obj, String name) {
		try {
			return obj.optJSONArray(name);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			obj = null;
			name = null;
		}

		return null;
	}

	public static JSONObject readObj(JSONArray jArr, int index) {
		try {
			return jArr.getJSONObject(index);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jArr = null;
		}
		return null;
	}

	public static JSONObject readObj(JSONObject jObj, String name) {
		try {
			return jObj.getJSONObject(name);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jObj = null;
		}
		return null;
	}

	public static String readString(JSONObject jObj, String key, String _defaultValue) {
		String rtnStr = _defaultValue;
		if (jObj == null || TextUtils.isEmpty(key))
			return rtnStr;
		try {
			if (jObj.has(key)) {
				rtnStr = jObj.optString(key, _defaultValue);
				rtnStr = StringUtils.maskNull(rtnStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jObj = null;
			key = null;
		}
		return rtnStr;
	}

	public static String readString(JSONObject jObj, String key) {
		return readString(jObj, key, "");
	}

	public static int readInt(JSONObject jObj, String key, int _defalutValue) {
		if (jObj == null || TextUtils.isEmpty(key))
			return _defalutValue;
		try {
			if (jObj.has(key))
				return jObj.optInt(key, _defalutValue);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jObj = null;
			key = null;
		}
		return _defalutValue;
	}

	public static int readInt(JSONObject jObj, String key) {
		return readInt(jObj, key, 0);
	}

	public static long readLong(JSONObject jObj, String key, long _defaultValue) {
		if (jObj == null || TextUtils.isEmpty(key))
			return _defaultValue;
		try {
			if (jObj.has(key)) {
				return jObj.optLong(key, _defaultValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jObj = null;
			key = null;
		}
		return _defaultValue;
	}

	public static long readLong(JSONObject jObj, String key) {
		return readLong(jObj, key, 0);
	}
}
