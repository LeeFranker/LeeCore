package thread.http;

import thread.callback.HttpRequestCallback;
import android.os.AsyncTask;

public abstract class AbsAsyncTask extends AsyncTask<Object, Integer, Object> {

	public HttpRequestCallback mCallback;

	public AbsAsyncTask(HttpRequestCallback callback) {
		this.mCallback = callback;
	}

	@Override
	protected void onPreExecute() {
		if (null != mCallback) {
			mCallback.onPreExecuteCallBack();
		}
	}

	@Override
	protected Object doInBackground(Object... params) {
		return process(params);
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		if (null != mCallback) {
			mCallback.onProgressUpdateCallBack(values);
		}
	}

	@Override
	protected void onPostExecute(Object result) {
		if (null != mCallback) {
			mCallback.onPostExecuteCallBack(result);
		}
	}

	protected abstract Object process(Object... params);

}
