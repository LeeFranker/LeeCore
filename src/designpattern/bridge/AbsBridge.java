package designpattern.bridge;

public abstract class AbsBridge {
	private IfaceSource source;

	public AbsBridge(IfaceSource source) {
		this.source = source;
	}

	public abstract void method();

	public IfaceSource getSource() {
		return source;
	}

	public void setSource(IfaceSource source) {
		this.source = source;
	}

}
