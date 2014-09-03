package designpattern.state;

public class Context {
	public State state;

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public void method() {
		if (state.getState().equals("1"))
			state.method1();
		else if (state.getState().equals("2"))
			state.method2();
	}
}
