package designpattern.flyweight;

public class Test {
	public void test(){
		FlyWeightFactory factory=new FlyWeightFactory();
		FlyWeight fly=factory.factory("1");
		fly.method("1");
		
		fly=factory.factory("2");
		fly.method("2");
		
		fly=factory.factory("3");
		fly.method("3");
	}
}
