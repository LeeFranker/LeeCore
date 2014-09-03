package designpattern.prototype;

public class Prototype implements Cloneable {

	@Override
	protected Object clone() throws CloneNotSupportedException {
		Prototype proto = (Prototype) super.clone();
		return proto;
	}

}
