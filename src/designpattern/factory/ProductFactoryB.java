package designpattern.factory;

public class ProductFactoryB extends AbsFactory{

	@Override
	public Product createProduct() {
		return new ProductB();
	}
	
}
