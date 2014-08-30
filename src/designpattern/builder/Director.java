package designpattern.builder;

public class Director {
	private AbsBuilder builder;

	public Director(AbsBuilder builder) {
		this.builder = builder;
	}

	public AbsProduct create() {
		return builder.buildProduct();
	}
}
