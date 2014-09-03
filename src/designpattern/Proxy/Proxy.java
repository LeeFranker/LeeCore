package designpattern.Proxy;

public class Proxy implements IfaceSource {

	private IfaceSource source;

	public Proxy() {
		super();
		source = new Source();
	}

	@Override
	public void method() {
		before();
		source.method();
		after();
	}

	public void before() {
		System.out.println("before");
	}

	public void after() {
		System.out.println("after");
	}

}
