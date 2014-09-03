package designpattern.bridge;

public class BridgeImplA extends AbsBridge {

	public BridgeImplA(IfaceSource source) {
		super(source);
	}

	@Override
	public void method() {
		getSource().method();
	}
	
	public void doMehtod_A(){
		System.out.println("do_method_A");
	}

}
