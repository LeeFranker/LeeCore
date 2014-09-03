package designpattern.Interpreter;

public class Test {
	public void test(){
		new InterpreterA().interpreter(new Context(1,2));
		new InterpreterA().interpreter(new Context(1, 2));
	}
}
