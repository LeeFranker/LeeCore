package thread;

import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.cookie.Cookie;
import org.json.JSONArray;
import org.json.JSONObject;

import utils.StringUtils;
import android.content.Context;
import android.text.TextUtils;

public abstract class AbsIfaceDataTask implements JDataTask, JParamName, JResultCode {

	protected final String TAG = this.getClass().getSimpleName();

	protected HashMap<String, String> getRquestHeader() {
		return null;
	}

	protected void setRequestHeader(HashMap<String, String> requestHeader) {

	}

	protected void addCookie(Cookie cookie) {

	}

	protected void cleanCookie() {

	}

	protected boolean isGet() {
		return true;
	}

	protected List<? extends NameValuePair> getNameValue(Context context, Object... params) {
		return null;
	}

	public abstract Object paras(Context context, Object obj);

	public abstract String getUrl(Context context, Object... params);

	public abstract boolean todo(Context context, AbsOnAnyTimeCallBack callback, Object... objects);

	public abstract int getMethod();

	protected boolean readBoolean(JSONObject obj, String key, boolean _defaultValue) {

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

	protected JSONArray readArr(JSONObject obj, String name) {
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

	protected JSONObject readObj(JSONArray jArr, int index) {
		try {
			return jArr.getJSONObject(index);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jArr = null;
		}
		return null;
	}

	protected JSONObject readObj(JSONObject jObj, String name) {
		try {
			return jObj.getJSONObject(name);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jObj = null;
		}
		return null;
	}

	protected String readString(JSONObject jObj, String key, String _defaultValue) {
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

	protected String readString(JSONObject jObj, String key) {
		return readString(jObj, key, "");
	}

	protected int readInt(JSONObject jObj, String key, int _defalutValue) {
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

	protected int readInt(JSONObject jObj, String key) {
		return readInt(jObj, key, 0);
	}

	protected long readLong(JSONObject jObj, String key, long _defaultValue) {
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

	protected long readLong(JSONObject jObj, String key) {
		return readLong(jObj, key, 0);
	}

	/**
	 * 访问服务器
	 */
	public Object touchServer(Context context, String url) {
		return null;
	}
}
