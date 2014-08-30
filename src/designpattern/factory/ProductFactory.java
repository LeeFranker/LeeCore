package designpattern.factory;

//简单工厂
public class ProductFactory {
	public Product createProduct(String type){
		if("A".equals(type))
			return new ProductA();
		else if("B".equals(type))
			return new ProductB();
		else 
			return null;
	}
}
