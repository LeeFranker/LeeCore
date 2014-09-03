package designpattern.observe;

public interface Observable {
	public void add(Observe observe);
	
	public void del(Observe observe);
	
	public void notifyObserves();
	
	public void operation();
}
