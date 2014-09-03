package designpattern.template;

public abstract class AbsTemplate {

	public void method(){
		System.out.println(getName());
	}
	
	public abstract String getName();
	
}
