package designpattern.decorator;

public class Decorator implements IfaceSource{

	private IfaceSource source;
	
	public Decorator(IfaceSource source){
		this.source=source;
	}

	@Override
	public void method() {
		System.out.println("开始装饰");
		source.method();
		System.out.println("结束装饰");
		
	}
	
	
	
}
