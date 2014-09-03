package designpattern.factory;

public class ProductFactory_1 {
	public Product productA(){
		return new ProductA();
	}
	
	public Product productB(){
		return new ProductB();
	}
}
