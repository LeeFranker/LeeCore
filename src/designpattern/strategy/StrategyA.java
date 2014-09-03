package designpattern.strategy;

public class StrategyA implements IfaceStrategy {

	@Override
	public void operator() {
		System.out.println("A的策略");
	}

}
