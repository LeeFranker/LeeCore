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
	 * 返回HTTP结果
	 * 
	 * @param request
	 * @return
	 */
	HttpResponse execute(HttpRequest request);

	/**
	 * 终止HTTP请求
	 */
	void abort();

	/**
	 * 当前HttpClient是否停止
	 * 
	 * @return
	 */
	boolean isDisposed();

	/**
	 * 停止HttpClient
	 */
	void dispose();

	/**
	 * 添加cookie
	 * 
	 * @param cookie
	 */
	void addCookie(Cookie cookie);

	/**
	 * 设置cookie
	 * 
	 * @param cookie
	 */
	void setCookie(Cookie cookie);

	/**
	 * 清空cookie
	 */
	void clearCookie();

	/**
	 * 获取超时对设定值
	 */
	int getConnectionTimeOut();

	/**
	 * 设置超时设置值
	 */
	void setConnectionTimeOut(int connectionTimeOut);

	/**
	 * 获取响应超时设定值
	 * 
	 * @return
	 */
	int getResponseTimeOut();

	/**
	 * 设置响应超时设置值
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
	 * 取消HTTP请求过程的监听
	 * 
	 * @param listener
	 */
	void unregisterProgressListener(HttpProgressListener listener);
}
