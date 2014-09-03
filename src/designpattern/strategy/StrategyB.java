package designpattern.strategy;

public class StrategyB implements IfaceStrategy{

	@Override
	public void operator() {
		System.out.println("B的策略");
	}

}
