package designpattern.command;


public class Invoker {

	public Invoker(IfaceCommand command) {
		super();
		this.command = command;
	}

	private IfaceCommand command;

	public IfaceCommand getCommand() {
		return command;
	}

	public void setCommand(IfaceCommand command) {
		this.command = command;
	}

	public void action() {
		if (command != null)
			command.exe();
	}

}
