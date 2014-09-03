package designpattern.strategy;


public class Test {
	public void test(){
		IfaceStrategy strategy=new StrategyA();
		strategy.operator();
		
		strategy=new StrategyB();
		strategy.operator();
	}
}
