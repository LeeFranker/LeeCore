package test.ui;

import java.util.HashMap;

import thread.AbsHttpTask;

public class IpTask extends AbsHttpTask{
	String url="http://listso.m.areainfo.ppstream.com/ip/query.php";

	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		return url;
	}

	@Override
	public HashMap<String, String> getParams() {
		// TODO Auto-generated method stub
		return null;
	}

}
