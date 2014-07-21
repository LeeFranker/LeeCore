package http;

import org.apache.http.cookie.Cookie;

/**
 * 
 * @author LeeFranker
 * 
 */
public interface Http {

	/**
	 * ����HTTP����
	 * 
	 * @param url
	 * @return
	 */
	HttpRequest newRequest(String url);

	/**
	 * ����HTTP���
	 * 
	 * @param request
	 * @return
	 */
	HttpResponse execute(HttpRequest request);

	/**
	 * ��ֹHTTP����
	 */
	void abort();

	/**
	 * ��ǰHttpClient�Ƿ�ֹͣ
	 * 
	 * @return
	 */
	boolean isDisposed();

	/**
	 * ֹͣHttpClient
	 */
	void dispose();

	/**
	 * ���cookie
	 * 
	 * @param cookie
	 */
	void addCookie(Cookie cookie);

	/**
	 * ����cookie
	 * 
	 * @param cookie
	 */
	void setCookie(Cookie cookie);

	/**
	 * ���cookie
	 */
	void clearCookie();

	/**
	 * ��ȡ��ʱ���趨ֵ
	 */
	int getConnectionTimeOut();

	/**
	 * ���ó�ʱ����ֵ
	 */
	void setConnectionTimeOut(int connectionTimeOut);

	/**
	 * ��ȡ��Ӧ��ʱ�趨ֵ
	 * 
	 * @return
	 */
	int getResponseTimeOut();

	/**
	 * ������Ӧ��ʱ����ֵ
	 * 
	 * @param responseTimeOut
	 */
	void setResponseTimeOut(int responseTimeOut);

	/**
	 * ע��HTTP������̵ļ���
	 * 
	 * @param listener
	 */
	void registerProgressListener(HttpProgressListener listener);

	/**
	 * ȡ��HTTP������̵ļ���
	 * 
	 * @param listener
	 */
	void unregisterProgressListener(HttpProgressListener listener);
}
