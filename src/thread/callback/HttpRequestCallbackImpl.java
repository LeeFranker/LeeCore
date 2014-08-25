package thread.callback;

import android.content.Context;
import android.os.Handler;

public class HttpRequestCallbackImpl implements HttpRequestCallback {

	private HttpRequestCallback mRequestCallback;
	private Handler mHandler;

	public HttpRequestCallbackImpl(Handler handler, HttpRequestCallback requestCallback) {
		this.mRequestCallback = requestCallback;
		this.mHandler = handler;
	}

	public HttpRequestCallbackImpl(Context context, HttpRequestCallback requestCallback) {
		this.mRequestCallback = requestCallback;
		this.mHandler = new Handler(context.getMainLooper());
	}

	@Override
	public void onNetWorkExceptionCallBack() {
		if (mHandler == null)
			return;
		mHandler.post(new Runnable() {

			@Override
			public void run() {
				if (mRequestCallback != null)
					mRequestCallback.onNetWorkExceptionCallBack();

			}
		});

	}

	@Override
	public void onPreExecuteCallBack() {
		if (mHandler == null)
			return;
		mHandler.post(new Runnable() {

			@Override
			public void run() {
				if (mRequestCallback != null)
					mRequestCallback.onPreExecuteCallBack();

			}
		});

	}

	@Override
	public void onProgressUpdateCallBack(final Integer... objects) {
		if (mHandler == null)
			return;
		mHandler.post(new Runnable() {

			@Override
			public void run() {
				if (mRequestCallback != null)
					mRequestCallback.onProgressUpdateCallBack(objects);

			}
		});

	}

	@Override
	public void onCancelledCallBack(final Object... objects) {
		if (mHandler == null)
			return;
		mHandler.post(new Runnable() {

			@Override
			public void run() {
				if (mRequestCallback != null)
					mRequestCallback.onCancelledCallBack(objects);

			}
		});

	}

	@Override
	public void onPostExecuteCallBack(final Object object) {
		if (mHandler == null)
			return;
		mHandler.post(new Runnable() {

			@Override
			public void run() {
				if (mRequestCallback != null)
					mRequestCallback.onPostExecuteCallBack(object);

			}
		});

	}

}
