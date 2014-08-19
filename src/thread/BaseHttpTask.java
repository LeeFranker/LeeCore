package thread;

import thread.callback.HttpRequestCallback;
import android.content.Context;

/**
 * 基础HTTP任务类
 * 
 * @author LeeFranker
 * 
 */
public abstract class BaseHttpTask implements IfaceHttpTask, IfaceHttpParams, IfaceHttpResultCode {

	protected final String TAG = this.getClass().getSimpleName();

	/**
	 * 是否是Get请求
	 * 
	 * @return
	 */
	protected boolean isGet() {
		return true;
	}

	/**
	 * 解析数据
	 * 
	 * @param context
	 * @param obj
	 * @return
	 */
	public abstract Object paras(Context context, Object obj);

	/**
	 * 获取请求地址
	 * 
	 * @param context
	 * @param params
	 * @return
	 */
	public abstract String getUrl();

	/**
	 * 执行网络请求
	 * 
	 * @param context
	 * @param callback
	 * @param objects
	 * @return
	 */
	public abstract boolean todo(Context context, HttpRequestCallback callback, Object... objects);

}
