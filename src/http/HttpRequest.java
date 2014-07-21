package http;

import java.io.File;

public interface HttpRequest {

	public enum HttpMethod {
		GET, POST, PUT, DELETE
	}

	/**
	 * 设置请求的URL
	 * 
	 * @param url
	 * @return
	 */
	HttpRequest setUrl(String url);

	/**
	 * 获取请求的URL
	 * 
	 * @return
	 */
	String getUrl();

	/**
	 * 设置请求的类型
	 * 
	 * @param method
	 * @return
	 */
	HttpRequest setMethod(HttpMethod method);

	/**
	 * 获取请求的类型
	 * 
	 * @return
	 */
	HttpMethod getMethod();

	/**
	 * 设置请求的字符编码
	 * 
	 * @param charsetName
	 * @return
	 */
	boolean setCharset(String charsetName);

	/**
	 * 获取请求的字符编码
	 * 
	 * @return
	 */
	String getCharset();

	/**
	 * 添加字符串参数
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	HttpRequest addStringParams(String key, String value);

	/**
	 * 添加文件参数
	 * 
	 * @param key
	 * @param file
	 * @return
	 */
	HttpRequest addFileParams(String key, File file);

	/**
	 * 请求添加头的参数
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	HttpRequest addHeader(String key, String value);

}
