package designpattern.Interpreter;

public class InterpreterA implements IfaceInterpreter{

	@Override
	public void interpreter(Context context) {
		System.out.println(context.getA()-context.getB());
		
	}

}
