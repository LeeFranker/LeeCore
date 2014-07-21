package http;

import java.io.File;

public interface HttpRequest {

	public enum HttpMethod {
		GET, POST, PUT, DELETE
	}

	/**
	 * ���������URL
	 * 
	 * @param url
	 * @return
	 */
	HttpRequest setUrl(String url);

	/**
	 * ��ȡ�����URL
	 * 
	 * @return
	 */
	String getUrl();

	/**
	 * �������������
	 * 
	 * @param method
	 * @return
	 */
	HttpRequest setMethod(HttpMethod method);

	/**
	 * ��ȡ���������
	 * 
	 * @return
	 */
	HttpMethod getMethod();

	/**
	 * ����������ַ�����
	 * 
	 * @param charsetName
	 * @return
	 */
	boolean setCharset(String charsetName);

	/**
	 * ��ȡ������ַ�����
	 * 
	 * @return
	 */
	String getCharset();

	/**
	 * ����ַ�������
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	HttpRequest addStringParams(String key, String value);

	/**
	 * ����ļ�����
	 * 
	 * @param key
	 * @param file
	 * @return
	 */
	HttpRequest addFileParams(String key, File file);

	/**
	 * �������ͷ�Ĳ���
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	HttpRequest addHeader(String key, String value);

}
