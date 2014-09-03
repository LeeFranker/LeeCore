package designpattern.facade;

public class Facade {

	SourceA a = new SourceA();
	SourceB b = new SourceB();

	public void method1() {
		a.method1();
		b.method1();
	}

	public void method2() {
		a.method2();
		b.method2();
	}

}
