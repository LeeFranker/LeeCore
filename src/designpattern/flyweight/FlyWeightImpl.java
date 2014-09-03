package designpattern.flyweight;

public class FlyWeightImpl implements FlyWeight {

	private String flag;

	public FlyWeightImpl(String flag) {
		this.flag = flag;
	}

	@Override
	public void method(String state) {
		System.out.println("flag-->" + flag);
		System.out.println("state-->" + state);
	}

}
