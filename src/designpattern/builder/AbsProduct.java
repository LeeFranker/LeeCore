package designpattern.builder;

public abstract class AbsProduct {
	public abstract void doPart1();
	public abstract void doPart2();
	public abstract void doPart3();
	
	//默认组合方式
	public final AbsProduct defaultProduct(){
		doPart1();
		doPart2();
		doPart3();
		return this;
	}
	
}
