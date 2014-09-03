package designpattern.command;

public class Command implements IfaceCommand {

	public Command(Receiver receiver) {
		super();
		this.receiver = receiver;
	}

	public Receiver receiver;

	public Receiver getReceiver() {
		return receiver;
	}

	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}

	@Override
	public void exe() {

		if (receiver != null)
			receiver.action();

	}

}
