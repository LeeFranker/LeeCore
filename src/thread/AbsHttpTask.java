package thread;

import http.IfaceHttp;
import http.IfaceHttpRequest;
import http.IfaceHttpRequest.HttpMethod;
import http.IfaceHttpResponse;
import http.apache.HttpApache;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

import org.apache.http.protocol.HTTP;

import thread.callback.ExecuteRequestCallback;
import thread.callback.HttpRequestCallback;
import thread.callback.RequestCallbackImpl;
import thread.http.AbsAsyncTask;
import thread.threadpool.ExpandedThreadPool;
import utils.APIUtils;
import utils.StringUtils;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

/**
 * 基础HTTP任务
 * 
 * @author LeeFranker
 * 
 */
public abstract class AbsHttpTask extends HttpTask {

	/**
	 * 回调对象
	 */
	private HttpRequestCallback mCallback;

	/**
	 * 线程池对象
	 */
	private static ExpandedThreadPool mThreadPool;

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
	public boolean todo(Context context) {
		return todo(context, new RequestCallbackImpl());
	}

	@SuppressLint("NewApi")
	@Override
	public boolean todo(Context context, HttpRequestCallback callback) {

		toString();

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
		if (TextUtils.isEmpty(getUrl()))
			return null;
		else {
			if (getParams() != null && getParams().size() > 0) {
				StringBuilder sb = new StringBuilder(getUrl());
				if (getUrl().contains("?"))
					sb.append('&');
				else
					sb.append('?');
				Iterator<String> iterator = getParams().keySet().iterator();
				while (iterator.hasNext()) {
					String key = iterator.next();
					sb.append(key);
					sb.append('=');
					String value = getParams().get(key);
					if (TextUtils.isEmpty(value))
						value = "";
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
			} else
				return getUrl();
		}
	}

	public class AbsAsyncTaskImpl extends AbsAsyncTask {

		protected long mContentLength;

		protected InputStream mStream;

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

			if (StringUtils.isEmpty(getUrl()) || mContext == null)
				return null;

			return connectServer();
		}

		public Object connectServer() {

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

				} else if (!isGet() && null != getParams() && getParams().size() > 0) {

					request = mHttp.newRequest(getUrl());
					request.setMethod(HttpMethod.POST);
					request.addStringParams(getParams());
				}

				IfaceHttpResponse response = mHttp.execute(request);

				if (response == null){
					Log.d(TAG, "response==null");
					return null;
				}

				int responseCode = response.getStatusCode();

				Log.d(TAG, "responseCode:" + responseCode);

				if (responseCode != 200) {
					return null;
				}

				mContentLength = response.getContentLength();

				Log.d(TAG, "mContentLength:" + mContentLength);

				return response.getContent();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (mHttp != null)
					mHttp.dispose();
			}
			return null;
		}
	}

	@Override
	public String toString() {
		return getWrappedUrl();
	}

}
