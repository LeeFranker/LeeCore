package http;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;

import android.content.Context;
import android.text.TextUtils;

public abstract class BaseHttp implements IfaceHttp{

	
    protected Context mContext;
    protected String mUserAgent;
    protected CookieStore mCookieStore;
    protected List<IfaceHttpProgressListener> mProgressListeners;
    protected boolean mIsDisposed;// 标识是否通信线程池状态，是否已经关闭
    protected int mConnectionTimeOut;// 尝试建立连接的等待时间，默认为10秒。
    protected int mResponseTimeOut;// 等待数据返回时间，默认为10秒。
	
    public BaseHttp(Context context,String userAgent){
    	this.mContext=context;
    	this.mUserAgent=userAgent;
    	this.mConnectionTimeOut=15*1000;
    	this.mResponseTimeOut=15*1000;
    	this.mIsDisposed=false;
    	this.mProgressListeners=new ArrayList<IfaceHttpProgressListener>();
    	this.mCookieStore=new BasicCookieStore();
    }
	

	@Override
	public boolean isDisposed() {
		return mIsDisposed;
	}


	@Override
	public void addCookie(Cookie cookie) {
        if (cookie == null || TextUtils.isEmpty(cookie.getName()))
            return;

        mCookieStore.addCookie(cookie);
	}

	@Override
	public void setCookie(Cookie cookie) {
        if (cookie == null || TextUtils.isEmpty(cookie.getName()))
            return;

        List<Cookie> cookies = mCookieStore.getCookies();
        for (int i = 0; i < cookies.size(); i ++ ) {
            if (cookies.get(i).equals(cookie.getName())) {
                cookies.remove(i);
                break;
            }
        }
        mCookieStore.addCookie(cookie);
	}

	@Override
	public void clearCookie() {
		mCookieStore.clear();
	}

	@Override
	public int getConnectionTimeOut() {
		return mConnectionTimeOut;
	}

	@Override
	public void setConnectionTimeOut(int connectionTimeOut) {
		mConnectionTimeOut=connectionTimeOut;
	}

	@Override
	public int getResponseTimeOut() {
		return mResponseTimeOut;
	}

	@Override
	public void setResponseTimeOut(int responseTimeOut) {
		mResponseTimeOut=responseTimeOut;
	}

	@Override
	public void registerProgressListener(IfaceHttpProgressListener listener) {
		if(listener!=null)
			mProgressListeners.add(listener);
	}

	@Override
	public void unregisterProgressListener(IfaceHttpProgressListener listener) {
		if(listener!=null)
			mProgressListeners.remove(listener);
	}

}
