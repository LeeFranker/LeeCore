package http;

import org.apache.http.cookie.Cookie;

/**
 * 
 * @author LeeFranker
 * 
 */
public interface Http {

	/**
	 * 创建HTTP请求
	 * 
	 * @param url
	 * @return
	 */
	HttpRequest newRequest(String url);

	/**
	 * 执行HTTP请求
	 * 
	 * @param request
	 * @return
	 */
	HttpResponse execute(HttpRequest request);

	/**
	 * 终止当前的HTTP请求
	 */
	void abort();

	/**
	 * 
	 * 当前的HTTP请求是否停止
	 * 
	 * @return
	 */
	boolean isDisposed();

	/**
	 * 停止HTTP请求
	 */
	void dispose();

	/**
	 * 添加Cookie
	 * 
	 * @param cookie
	 */
	void addCookie(Cookie cookie);

	/**
	 * 设置Cookie
	 * 
	 * @param cookie
	 */
	void setCookie(Cookie cookie);

	/**
	 * 清空Cookie
	 */
	void clearCookie();

	/**
	 * 获取超时时间的设定值
	 */
	int getConnectionTimeOut();

	/**
	 * 设置超时时间
	 */
	void setConnectionTimeOut(int connectionTimeOut);

	/**
	 * 获取响应超时的设定值
	 * 
	 * @return
	 */
	int getResponseTimeOut();

	/**
	 * 设置响应超时的设置值
	 * 
	 * @param responseTimeOut
	 */
	void setResponseTimeOut(int responseTimeOut);

	/**
	 * 注册HTTP请求过程的监听
	 * 
	 * @param listener
	 */
	void registerProgressListener(HttpProgressListener listener);

	/**
	 * 取消HTTP请求过程对监听
	 * 
	 * @param listener
	 */
	void unregisterProgressListener(HttpProgressListener listener);
}
