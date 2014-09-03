package designpattern.visitor;

public class Visitor implements IfaceVisitor{

	@Override
	public void visit(Element element) {
		System.out.println("通过我了");
		element.method();
	}

}
