package test.ui;

import java.io.InputStream;

import thread.HttpTask;
import thread.callback.HttpRequestCallback;
import utils.StringUtils;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		HttpTask task = new IpTask();
		task.todo(this, new HttpRequestCallback() {

			@Override
			public void onProgressUpdateCallBack(Integer... objects) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPreExecuteCallBack() {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPostExecuteCallBack(Object object) {
				// TODO Auto-generated method stub

				if (object == null)
					Log.d("wangli", "-------->>>");
				else {
					InputStream is = (InputStream) object;
					Log.d("wangli", "--->" + StringUtils.getStrFromInput(is));
				}

			}

			@Override
			public void onNetWorkExceptionCallBack() {
				// TODO Auto-generated method stub

			}

			@Override
			public void onCancelledCallBack(Object... objects) {
				// TODO Auto-generated method stub

			}
		});

	}

}
