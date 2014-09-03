package designpattern.visitor;

public class ElementB extends Element{

	@Override
	public void accept(IfaceVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void method() {
		System.out.println("B");
	}

}
