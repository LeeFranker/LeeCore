package designpattern.factory;

public class ProductFactoryA extends AbsFactory {

	@Override
	public Product createProduct() {
		return new ProductA();
	}

}
