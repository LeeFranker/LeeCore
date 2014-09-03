package designpattern.state;

public class Test {
	public void test(){
		State state=new State();
		Context context=new Context();
		context.setState(state);
		
		state.setState("1");
		context.method();
		
		state.setState("2");
		context.method();
	}
}
