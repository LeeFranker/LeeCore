package designpattern.observe;

public class Test {
	public void test(){
		ObservableImpl ob=new ObservableImpl();
		ob.add(new ObserveA());
		ob.add(new ObserveB());
		ob.notifyObserves();
	}
}
