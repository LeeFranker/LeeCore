package designpattern.chain;

public class Test {
	public Test(){
		Handler h1=new Handler("h1");
		Handler h2=new Handler("h2");
		Handler h3=new Handler("h3");
		h1.setHandler(h2);
		h2.setHandler(h3);
		h1.operator();
	}
}
