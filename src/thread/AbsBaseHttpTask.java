package thread;

import http.IfaceHttp;
import http.IfaceHttpRequest;
import http.IfaceHttpRequest.HttpMethod;
import http.IfaceHttpResponse;
import http.apache.HttpApache;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.protocol.HTTP;

import thread.callback.HttpRequestCallback;
import thread.callback.HttpRequestCallbackImpl;
import thread.http.AbsAsyncTask;
import utils.StringUtils;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

/**
 * 基础HTTP任务
 * 
 * @author LeeFranker
 * 
 */
public abstract class AbsBaseHttpTask extends BaseHttpTask {

	/**
	 * 请求地址
	 */
	private String mUrl;

	/**
	 * 请求参数
	 */
	private HashMap<String, String> mParams = new HashMap<String, String>();

	/**
	 * 回调对象
	 */
	private HttpRequestCallback mCallback;

	/**
	 * 初始化请求地址
	 * 
	 * @return
	 */
	public abstract String initUrl();

	/**
	 * 初始化请求参数
	 */
	public abstract void initParames();

	/**
	 * 设置请求参数
	 * 
	 * @param params
	 */
	public void setParams(HashMap<String, String> params) {
		this.mParams = params;
	}

	/**
	 * 添加请求参数
	 * 
	 * @param key
	 * @param values
	 */
	public void putParams(String key, String values) {
		mParams.put(key, values);
	}

	/**
	 * 设置回调对象
	 * 
	 * @param handler
	 * @param callback
	 */
	public void setRequestCallback(Handler handler, HttpRequestCallback callback) {
		mCallback = new HttpRequestCallbackImpl(handler, callback);
	}

	/**
	 * 设置回调对象
	 * 
	 * @param context
	 * @param callback
	 */
	public void setRequestCallback(Context context, HttpRequestCallback callback) {
		mCallback = new HttpRequestCallbackImpl(context, callback);
	}

	@Override
	public Object paras(Context context, Object obj) {
		return null;
	}

	@Override
	public String getUrl() {
		return getWrappedUrl();
	}

	@Override
	public boolean todo(Context context, HttpRequestCallback callback, Object... objects) {

		mCallback = callback;

		return false;
	}

	/**
	 * 获取组装的请求地址
	 * 
	 * @return
	 */
	private String getWrappedUrl() {
		if (!TextUtils.isEmpty(getUrl()) && mParams != null) {
			StringBuilder sb = new StringBuilder(getUrl());
			if (getUrl().contains("?")) {
				sb.append('&');
			} else {
				sb.append('?');
			}
			for (Map.Entry<String, String> entry : mParams.entrySet()) {
				String key = entry.getKey().toString();
				String value = null;
				if (entry.getValue() == null) {
					value = "";
				} else {
					value = entry.getValue().toString();
				}
				sb.append(key);
				sb.append('=');
				try {
					value = URLEncoder.encode(value, HTTP.UTF_8);
					sb.append(value);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				sb.append('&');
			}
			sb.deleteCharAt(sb.length() - 1);// 删除最后一个"&"
			return sb.toString();
		}
		return null;
	}

	public class AbsAsyncTaskImpl extends AbsAsyncTask {

		protected String mUrl;

		protected long mContentLength;

		protected InputStream mInput;

		private Context mContext;

		public AbsAsyncTaskImpl(HttpRequestCallback callback) {
			super(callback);
		}

		@Override
		protected Object process(Object... params) {

			return connectServer(params);
		}

		protected Object connectServer(Object... params) {
			if (StringUtils.isEmptyArray(params)) {
				return null;
			}

			mUrl = String.valueOf(params[0]);
			if (StringUtils.isEmpty(mUrl)) {
				return null;
			}

			Object object = connectServer(mContext, mUrl, 0);

			return object;
		}

		public Object connectServer(Context mContext, String url, int mTimeOut) {
			if (null != mContext && !StringUtils.isEmpty(url)) {

				IfaceHttp mHttp = null;
				try {
					mHttp = new HttpApache(mContext, FIXED_USERAGENT);
					if (mTimeOut > 0) {
						mHttp.setConnectionTimeOut(mTimeOut);
						mHttp.setResponseTimeOut(mTimeOut);
					}


					IfaceHttpRequest request = mHttp.newRequest(mUrl);

					if (isGet()) {
						request.setMethod(HttpMethod.GET);

					} else if (!isGet() && null != mParams && mParams.size() > 0) {
						request.setMethod(HttpMethod.POST);
						request.addStringParams(mParams);
					}

					IfaceHttpResponse response = mHttp.execute(request);

					if (null == response) {
						return -21;
					}

					int responseCode = response.getStatusCode();

					if (responseCode != 200) {
						return null;
					}

					mContentLength = response.getContentLength();
					if (mContentLength <= 0) {

					}

					mInput = response.getContent();

					if (null == mInput) {
						return null;
					}

					return mInput;
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (null != mHttp) {
						mHttp.dispose();
					}
				}
			}
			return null;
		}
	}
}
