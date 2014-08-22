package test.ui;

import thread.AbsHttpTask;

public class IpTask extends AbsHttpTask{
	String url="http://listso.m.areainfo.ppstream.com/ip/query.php";
	
	@Override
	public String initUrl() {
		return url;
	}

	@Override
	public void initParames() {
		
	}
	
	

}
