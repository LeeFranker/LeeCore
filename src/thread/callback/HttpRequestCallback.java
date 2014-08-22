package thread.callback;

/**
 * 监听网络请求任务的全过程
 * 
 * @author LeeFranker
 * 
 */
public interface HttpRequestCallback {

	public void onNetWorkExceptionCallBack();

	public void onPreExecuteCallBack();

	public void onProgressUpdateCallBack(Integer... objects);

	public void onCancelledCallBack(Object... objects);

	public void onPostExecuteCallBack(Object object);

}
