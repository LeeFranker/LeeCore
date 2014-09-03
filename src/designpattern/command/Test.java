package designpattern.command;


public class Test {

	public void test() {
		Receiver receiver = new Receiver();
		Command command = new Command(receiver);
		Invoker invoker = new Invoker(command);
		invoker.action();
	}
}
