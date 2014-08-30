package designpattern.builder;

public class ABuilder extends AbsBuilder{

	private AbsProduct productA=new ProductA();
	
	@Override
	public void buildPart() {
		// TODO Auto-generated method stub
		productA.doPart1();
		productA.doPart2();
		productA.doPart3();
	}

	@Override
	public AbsProduct buildProduct() {
		// TODO Auto-generated method stub
		return productA;
	}

}
