package http;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public abstract class BaseHttpResponse implements IfaceHttpResponse {

	private int mStatusCode;
	private Map<String, List<String>> mAllHeaders;
	private InputStream mInputStream;
	private long mContentLength;

	public BaseHttpResponse() {
		mStatusCode = -1;
		mContentLength = 0;
	}

	public void setStatusCode(int statusCode) {
		mStatusCode = statusCode;
	}

	public void setAllHeaders(Map<String, List<String>> headers) {
		mAllHeaders = headers;
	}

	public void setContent(InputStream inputStream) {
		mInputStream = inputStream;
	}

	public void setContentLength(long contentLength) {
		mContentLength = contentLength;
	}

	@Override
	public int getStatusCode() {
		return mStatusCode;
	}

	@Override
	public Map<String, List<String>> getAllHeads() {
		return mAllHeaders;
	}

	@Override
	public List<String> getHeader(String name) {
		if (mAllHeaders == null)
			return null;
		else
			return mAllHeaders.get(name);
	}

	@Override
	public InputStream getContent() {
		return mInputStream;
	}

	@Override
	public void consumeContent() {
		if (mInputStream != null)
			try {
				mInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	@Override
	public long getContentLength() {
		return mContentLength;
	}

	@Override
	public boolean isRepeatable() {
		return false;
	}

}
