package designpattern.chain;

public abstract class AbsHandler {

	private IfaceHandler handler;

	public IfaceHandler getHandler() {
		return handler;
	}

	public void setHandler(IfaceHandler handler) {
		this.handler = handler;
	}
	
	
}
