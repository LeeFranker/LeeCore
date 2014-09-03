package designpattern.state;

public class State {
	public String state;
	public void method1(){
		System.out.println("method1");
	}
	public void method2(){
		System.out.println("method2");
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
