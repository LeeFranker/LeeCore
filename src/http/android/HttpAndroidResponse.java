package http.android;

import java.net.HttpURLConnection;
import java.nio.charset.Charset;

import http.BaseHttpResponse;

public class HttpAndroidResponse extends BaseHttpResponse {
	private HttpURLConnection mConnection;
	private Charset mCharset;

	protected void setConnection(HttpURLConnection connection) {
		mConnection = connection;
	}

	protected void setContentType(Charset charset) {
		mCharset = charset;
	}

	@Override
	public void consumeContent() {
		super.consumeContent();
		if (mConnection != null)
			mConnection.disconnect();
	}

	@Override
	public Charset getContentType() {
		return mCharset;
	}
}
