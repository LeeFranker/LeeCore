package designpattern.visitor;

public abstract class Element {
	public abstract void accept(IfaceVisitor visitor);
	public abstract void method();
}
