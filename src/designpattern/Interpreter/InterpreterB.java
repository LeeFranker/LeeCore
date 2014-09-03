package designpattern.Interpreter;

public class InterpreterB implements IfaceInterpreter{

	@Override
	public void interpreter(Context context) {
		System.out.println(context.getA()+context.getB());
		
	}

}
