package http;

import java.io.File;
import java.util.HashMap;

public interface IfaceHttpRequest {

	public enum HttpMethod {
		GET, POST, PUT, DELETE
	}

	/**
	 * 设置请求的URL
	 * 
	 * @param url
	 * @return
	 */
	IfaceHttpRequest setUrl(String url);

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
	IfaceHttpRequest setMethod(HttpMethod method);

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
	 * 获取字符编码
	 * 
	 * @return
	 */
	String getCharset();

	/**
	 * 添加字符串的参数
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	IfaceHttpRequest addStringParams(String key, String value);

	/**
	 * 添加字符串的参数
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	IfaceHttpRequest addStringParams(HashMap<String, String> params);

	/**
	 * 添加文件参数
	 * 
	 * @param key
	 * @param file
	 * @return
	 */
	IfaceHttpRequest addFileParams(String key, File file);

	/**
	 * 添加文件参数
	 * 
	 * @param key
	 * @param file
	 * @return
	 */
	IfaceHttpRequest addFileParams(HashMap<String, File> params);

	/**
	 * 添加请求头的参数
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	IfaceHttpRequest addHeader(String key, String value);

	/**
	 * 添加请求头的参数
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	IfaceHttpRequest addHeader(HashMap<String, String> header);

}
