package designpattern.observe;

import java.util.Iterator;
import java.util.Vector;

public abstract class AbsObservable implements Observable{

	private Vector<Observe> vector=new Vector<Observe>();
	
	@Override
	public void add(Observe observe) {
		if(observe!=null)
			vector.add(observe);
	}

	@Override
	public void del(Observe observe) {
		if(observe!=null)
			vector.remove(observe);
		
	}

	@Override
	public void notifyObserves() {
		Iterator<Observe> iterator=vector.iterator();
		while(iterator.hasNext()){
			iterator.next().update();
		}
		
	}

	
	
}
