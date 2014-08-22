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
import thread.callback.ExecuteRequestCallback;
import thread.http.AbsAsyncTask;
import thread.threadpool.ExpandedThreadPool;
import utils.APIUtils;
import utils.StringUtils;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.text.TextUtils;

/**
 * 基础HTTP任务
 * 
 * @author LeeFranker
 * 
 */
public abstract class AbsHttpTask extends HttpTask {

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
	 * 线程池对象
	 */
	private static ExpandedThreadPool mThreadPool;

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
		mCallback = new ExecuteRequestCallback(handler, callback);
	}

	/**
	 * 设置回调对象
	 * 
	 * @param context
	 * @param callback
	 */
	public void setRequestCallback(Context context, HttpRequestCallback callback) {
		mCallback = new ExecuteRequestCallback(context, callback);
	}

	@Override
	public Object paras(Context context, Object obj) {
		return null;
	}

	@Override
	public String getUrl() {
		return mUrl;
	}

	@Override
	public boolean todo(Context context) {
		return todo(context, null);
	}

	@SuppressLint("NewApi")
	@Override
	public boolean todo(Context context, HttpRequestCallback callback) {

		setRequestCallback(context, callback);

		AbsAsyncTaskImpl mHttpTask;

		mHttpTask = new AbsAsyncTaskImpl(context, mCallback);

		if (APIUtils.hasHoneycomb()) {
			if (mThreadPool == null)
				mThreadPool = new ExpandedThreadPool();
			if (mThreadPool == null)
				mHttpTask.executeOnExecutor(mThreadPool);
			else
				mHttpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		} else {
			mHttpTask.execute();
		}

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

		protected long mContentLength;

		protected InputStream mInput;

		private Context mContext;

		private int mTimeOut;

		public AbsAsyncTaskImpl(Context context, HttpRequestCallback callback) {
			super(callback);
			this.mContext = context;
		}

		public AbsAsyncTaskImpl(Context context, HttpRequestCallback callback, int timeOut) {
			super(callback);
			this.mContext = context;
			this.mTimeOut = timeOut;
		}

		@Override
		protected Object process(Object... params) {

			return connectServer(params);
		}

		protected Object connectServer(Object... params) {

			if (StringUtils.isEmpty(getUrl())) {
				return null;
			}

			Object object = connectServer(mContext, getUrl(), 0);

			return object;
		}

		public Object connectServer() {
			if (null != mContext && !StringUtils.isEmpty(getUrl())) {

				IfaceHttp mHttp = null;
				try {
					mHttp = new HttpApache(mContext, FIXED_USERAGENT);
					if (mTimeOut > 0) {
						mHttp.setConnectionTimeOut(mTimeOut);
						mHttp.setResponseTimeOut(mTimeOut);
					}

					IfaceHttpRequest request = null;

					if (isGet()) {
						request = mHttp.newRequest(getWrappedUrl());
						request.setMethod(HttpMethod.GET);

					} else if (!isGet() && null != mParams && mParams.size() > 0) {

						request = mHttp.newRequest(getUrl());
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
