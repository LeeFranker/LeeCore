package test.ui;

import java.io.InputStream;

import thread.HttpTask;
import thread.callback.HttpRequestCallback;
import utils.StringUtils;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				doit();
			}
		});
	}

	public void doit() {
		HttpTask task = new IpTask();
		task.todo(this, new HttpRequestCallback() {

			@Override
			public void onProgressUpdateCallBack(Integer... objects) {
				// TODO Auto-generated method stub
				Log.d("wangli", "onProgressUpdateCallBack");
			}

			@Override
			public void onPreExecuteCallBack() {
				// TODO Auto-generated method stub
				Log.d("wangli", "onPreExecuteCallBack");
			}

			@Override
			public void onPostExecuteCallBack(Object object) {
				// TODO Auto-generated method stub

				Log.d("wangli", "onPostExecuteCallBack");

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
				Log.d("wangli", "onNetWorkExceptionCallBack");
			}

			@Override
			public void onCancelledCallBack(Object... objects) {
				// TODO Auto-generated method stub
				Log.d("wangli", "onCancelledCallBack");
			}
		});
	}

}
