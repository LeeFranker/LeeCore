package http;

public interface IfaceHttpProgressListener {

	void onSendRequest(IfaceHttpRequest request);

	void onReceiveResponse(IfaceHttpResponse response);

	void onException(Exception e);
	
	void onNetworkException();
}
