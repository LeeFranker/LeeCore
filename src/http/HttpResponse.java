package http;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

public interface HttpResponse {
	/**
	 * 返回响应的状态码
	 * 
	 * @return
	 */
	int getStatusCode();

	/**
	 * 获取响应头的所有键值对
	 * 
	 * @return
	 */
	Map<String, List<String>> getAllHeads();

	/**
	 * 获取响应头对某个键值对
	 * 
	 * @param name
	 * @return
	 */
	List<String> getHeader(String name);

	/**
	 * 获取响应的内容
	 * 
	 * @return
	 */
	InputStream getContent();

	/**
	 * 释放响应的内容
	 */
	void consumeContent();

	/**
	 * 获取响应内容的长度
	 * 
	 * @return
	 */
	long getContentLength();

	/**
	 * 获取响应的字符编码
	 * 
	 * @return
	 */
	Charset getContentType();

	/**
	 * 响应的内容是否可以获取多个
	 * 
	 * @return
	 */
	boolean isRepeatable();

}
