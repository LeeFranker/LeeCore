package http;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

public interface HttpResponse {
	/**
	 * ������Ӧ��״̬��
	 * 
	 * @return
	 */
	int getStatusCode();

	/**
	 * ��ȡ��Ӧͷ�����м�ֵ��
	 * 
	 * @return
	 */
	Map<String, List<String>> getAllHeads();

	/**
	 * ��ȡ��Ӧͷ��ĳ����ֵ��
	 * 
	 * @param name
	 * @return
	 */
	List<String> getHeader(String name);

	/**
	 * ��ȡ��Ӧ������
	 * 
	 * @return
	 */
	InputStream getContent();

	/**
	 * �ͷ���Ӧ������
	 */
	void consumeContent();

	/**
	 * ��ȡ��Ӧ���ݵĳ���
	 * 
	 * @return
	 */
	long getContentLength();

	/**
	 * ��ȡ��Ӧ���ַ�����
	 * 
	 * @return
	 */
	Charset getContentType();

	/**
	 * ��Ӧ�������Ƿ���Ի�ȡ���
	 * 
	 * @return
	 */
	boolean isRepeatable();

}
