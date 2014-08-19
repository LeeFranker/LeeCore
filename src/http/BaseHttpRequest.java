package http;

import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseHttpRequest implements IfaceHttpRequest {

	private String mUrl;
	private HttpMethod mMethod;
	private String mCharsetName;
	private Map<String, String> mStringParams;
	private Map<String, File> mFileParams;
	private Map<String, String> mHeaders;

	public BaseHttpRequest() {
		this.mMethod = HttpMethod.GET;
	}

	@Override
	public IfaceHttpRequest setUrl(String url) {
		this.mUrl = url;
		return this;
	}

	@Override
	public String getUrl() {
		return mUrl;
	}

	@Override
	public IfaceHttpRequest setMethod(HttpMethod method) {
		mMethod = method;
		return this;
	}

	@Override
	public HttpMethod getMethod() {
		return mMethod;
	}

	@Override
	public boolean setCharset(String charsetName) {
		mCharsetName = charsetName;
		return Charset.isSupported(mCharsetName);
	}

	@Override
	public String getCharset() {
		return mCharsetName;
	}

	@Override
	public IfaceHttpRequest addStringParams(String key, String value) {
		if (mStringParams == null)
			mStringParams = new HashMap<String, String>();
		mStringParams.put(key, value);
		return this;
	}

	@Override
	public IfaceHttpRequest addFileParams(String key, File file) {
		if (mFileParams == null)
			mFileParams = new HashMap<String, File>();
		mFileParams.put(key, file);
		return this;
	}

	@Override
	public IfaceHttpRequest addHeader(String key, String value) {
		if (mHeaders == null)
			mHeaders = new HashMap<String, String>();
		mHeaders.put(key, value);
		return this;
	}

	public Map<String, String> getStringParams() {
		return mStringParams;
	}

	public Map<String, File> getFileParams() {
		return mFileParams;
	}

	public Map<String, String> getHeaders() {
		return mHeaders;
	}

	@Override
	public IfaceHttpRequest addStringParams(HashMap<String, String> params) {
		if (params == null)
			params = new HashMap<String, String>();
		mStringParams.putAll(params);
		return this;
	}

	@Override
	public IfaceHttpRequest addFileParams(HashMap<String, File> params) {
		if (params == null)
			params = new HashMap<String, File>();
		mFileParams.putAll(params);
		return this;
	}

	@Override
	public IfaceHttpRequest addHeader(HashMap<String, String> header) {
		if (header == null)
			header = new HashMap<String, String>();
		mHeaders.putAll(header);
		return this;
	}

}
