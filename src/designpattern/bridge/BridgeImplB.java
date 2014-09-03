package designpattern.bridge;

public class BridgeImplB extends AbsBridge {

	public BridgeImplB(IfaceSource source) {
		super(source);

	}

	@Override
	public void method() {
		getSource().method();

	}

	public void doMethodB() {
		System.out.println("do_method_B");
	}

}
