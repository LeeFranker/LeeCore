package http.android;

import http.BaseHttp;
import http.IfaceHttpProgressListener;
import http.IfaceHttpRequest;
import http.IfaceHttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.http.cookie.Cookie;
import org.apache.http.entity.mime.MIME;

import utils.NetworkUtils;

import android.content.Context;

public class HttpAndroid extends BaseHttp {

	private HttpURLConnection mCurrentRequest;

	public HttpAndroid(Context context, String userAgent) {
		super(context, userAgent);
	}

	@Override
	public IfaceHttpRequest newRequest(String url) {
		return new HttpAndroidRequest().setUrl(url);
	}

	@Override
	public IfaceHttpResponse execute(IfaceHttpRequest req) {
		if (req == null || !(req instanceof HttpAndroidRequest))
			throw new IllegalArgumentException("HttpAndroidRequest is not correct!");

		if (mIsDisposed)
			throw new IllegalStateException("HttpAndroidRequest has been disposed!");

		if (!NetworkUtils.getIsNetworkConnected(mContext)) {
			for (IfaceHttpProgressListener listener : mProgressListeners)
				listener.onNetworkException();
			return null;
		}

		// 构造HttpRequest
		HttpAndroidRequest javaRequest = (HttpAndroidRequest) req;
		javaRequest.setTimeOut(mConnectionTimeOut, mResponseTimeOut);
		javaRequest.setUserAgent(mUserAgent);
		javaRequest.setCookies(mCookieStore.getCookies());// 设置request的cookie
		HttpURLConnection request = javaRequest.toJavaHttpRequest();
		mCurrentRequest = request;

		for (IfaceHttpProgressListener listener : mProgressListeners)
			listener.onSendRequest(req);

		try {
			// 和服务器通信
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				// 获取Response中的Cookie，并存入CookieStore
				List<String> cookies = request.getHeaderFields().get("Set-Cookie");
				if (cookies != null) {
					for (int i = 0; i < cookies.size(); i++) {
						Cookie cookie = HttpAndroidUtils.createCookie(cookies.get(i));
						if (cookie != null)
							mCookieStore.addCookie(cookie);
					}
				}
				// 设置Response的Charset
				Charset charset = null;
				List<String> contentTypeStrs = request.getHeaderFields().get(MIME.CONTENT_TYPE);
				if (contentTypeStrs != null && contentTypeStrs.size() > 0)
					charset = HttpAndroidUtils.getResponseCharset(contentTypeStrs.get(0));

				// 构造HttpResponse
				HttpAndroidResponse response = new HttpAndroidResponse();
				response.setConnection(request);
				response.setStatusCode(request.getResponseCode());
				response.setContent(inputStream);
				response.setContentLength(request.getContentLength());
				response.setContentType(charset);
				response.setAllHeaders(request.getHeaderFields());
				for (IfaceHttpProgressListener listener : mProgressListeners)
					listener.onReceiveResponse(response);

				return response;
			}
		} catch (IOException e) {
			e.printStackTrace();
			for (IfaceHttpProgressListener listener : mProgressListeners)
				listener.onException(e);
		} finally {
			// TIP 不能在此disconnect，会关闭InputStream
			mCurrentRequest = null;
		}
		return null;
	}

	@Override
	public void abort() {
		if (mCurrentRequest != null) {
			mCurrentRequest.disconnect();
			mCurrentRequest = null;
		}
	}

	@Override
	public void dispose() {
		if (!mIsDisposed) {
			abort();
			mContext = null;
			mIsDisposed = true;
			clearCookie();
		}
	}

}
