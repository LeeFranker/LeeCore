package http.apache;

import http.BaseHttpRequest;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.message.BasicNameValuePair;

import android.text.TextUtils;

public class HttpApacheRequest extends BaseHttpRequest {
	private IfaceHttpApacheTransferListener mListener;

	protected HttpApacheRequest() {
		super();
	}

	protected HttpApacheRequest setListener(IfaceHttpApacheTransferListener listener) {
		this.mListener = listener;
		return this;
	}

	public HttpRequest toApacheHttpRequest() {
		HttpUriRequest request = null;
		switch (getMethod()) {
		case GET:
			request = createHttpGet();
			break;
		case POST:
			request = createHttpPost();
			break;
		case PUT:
			request = createHttpPut();
			break;
		case DELETE:
			request = createHttpDelete();
			break;
		}
		return request;
	}

	private HttpGet createHttpGet() {
		if (TextUtils.isEmpty(getUrl()))
			return null;

		HttpGet httpGet = new HttpGet(getUrl());
		if (getHeaders() != null) {
			for (Map.Entry<String, String> header : getHeaders().entrySet())
				httpGet.addHeader(header.getKey(), header.getValue());
		}
		return httpGet;
	}

	private HttpPost createHttpPost() {
		if (TextUtils.isEmpty(getUrl()))
			return null;

		HttpPost httpPost = new HttpPost(getUrl());
		if (getHeaders() != null) {
			for (Map.Entry<String, String> header : getHeaders().entrySet())
				httpPost.addHeader(header.getKey(), header.getValue());
		}
		// 含有上传文件
		if (getFileParams() != null) {
			HttpApacheMultipartEntity reqEntity = new HttpApacheMultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, mListener);
			try {
				for (Map.Entry<String, File> fileParam : getFileParams().entrySet())
					reqEntity.addPart(fileParam.getKey(), new FileBody(fileParam.getValue()));
				if (getStringParams() != null) {
					for (Map.Entry<String, String> strParam : getStringParams().entrySet()) {
						if (getCharset() != null)
							reqEntity.addPart(strParam.getKey(), new StringBody(strParam.getValue(), Charset.forName(getCharset())));
						else
							reqEntity.addPart(strParam.getKey(), new StringBody(strParam.getValue()));
					}
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			httpPost.setEntity(reqEntity);
		}
		// 不含上传文件，只有字符串参数
		else {
			if (getStringParams() != null) {
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				for (Map.Entry<String, String> strParam : getStringParams().entrySet())
					params.add(new BasicNameValuePair(strParam.getKey(), strParam.getValue()));
				try {
					if (getCharset() != null)
						httpPost.setEntity(new UrlEncodedFormEntity(params, getCharset()));
					else
						httpPost.setEntity(new UrlEncodedFormEntity(params));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		return httpPost;
	}

	private HttpPut createHttpPut() {
		if (TextUtils.isEmpty(getUrl()))
			return null;

		HttpPut httpPut = new HttpPut(getUrl());
		if (getHeaders() != null) {
			for (Map.Entry<String, String> header : getHeaders().entrySet())
				httpPut.addHeader(header.getKey(), header.getValue());
		}
		// 含有上传文件
		if (getFileParams() != null) {
			MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
			try {
				for (Map.Entry<String, File> fileParam : getFileParams().entrySet())
					reqEntity.addPart(fileParam.getKey(), new FileBody(fileParam.getValue()));
				if (getStringParams() != null) {
					for (Map.Entry<String, String> strParam : getStringParams().entrySet()) {
						if (getCharset() != null)
							reqEntity.addPart(strParam.getKey(), new StringBody(strParam.getValue(), Charset.forName(getCharset())));
						else
							reqEntity.addPart(strParam.getKey(), new StringBody(strParam.getValue()));
					}
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			httpPut.setEntity(reqEntity);
		}
		// 不含上传文件，只有字符串参数
		else {
			if (getStringParams() != null) {
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				for (Map.Entry<String, String> strParam : getStringParams().entrySet())
					params.add(new BasicNameValuePair(strParam.getKey(), strParam.getValue()));
				try {
					if (getCharset() != null)
						httpPut.setEntity(new UrlEncodedFormEntity(params, getCharset()));
					else
						httpPut.setEntity(new UrlEncodedFormEntity(params));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		return httpPut;
	}

	private HttpDelete createHttpDelete() {
		if (TextUtils.isEmpty(getUrl()))
			return null;

		HttpDelete httpDelete = new HttpDelete(getUrl());
		if (getHeaders() != null) {
			for (Map.Entry<String, String> header : getHeaders().entrySet())
				httpDelete.addHeader(header.getKey(), header.getValue());
		}
		return httpDelete;
	}

}
