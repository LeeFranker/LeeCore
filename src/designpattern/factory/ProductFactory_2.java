package designpattern.factory;

//简单工厂
public class ProductFactory_2 {
	public static Product productA(){
		return new ProductA();
	}
	
	public static Product productB(){
		return new ProductB();
	}
}
