package thread;

import java.util.HashMap;

import thread.callback.HttpRequestCallback;
import android.content.Context;

/**
 * 基础HTTP任务类
 * 
 * @author LeeFranker
 * 
 */
public abstract class HttpTask implements IfaceHttpTask, IfaceHttpParams, IfaceHttpResultCode {

	protected final String TAG = "wangli";

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
	 * @return
	 */
	public abstract String getUrl();

	/**
	 * 获取请求参数
	 * 
	 * @return
	 */
	public abstract HashMap<String, String> getParams();

	/**
	 * 执行网络请求
	 * 
	 * @param context
	 * @param callback
	 * @return
	 */
	public abstract boolean todo(Context context, HttpRequestCallback callback);

	/**
	 * 执行网络请求
	 * 
	 * @param context
	 * @return
	 */
	public abstract boolean todo(Context context);

	/**
	 * 打印参数内容
	 * 
	 * @return
	 */
	public abstract String toString();

}
