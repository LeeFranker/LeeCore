package designpattern.flyweight;

import java.util.HashMap;

public class FlyWeightFactory {

	private HashMap<String, FlyWeight> map = new HashMap<String, FlyWeight>();

	public FlyWeight factory(String state) {
		FlyWeight fly = map.get(state);
		if (fly == null) {
			fly = new FlyWeightImpl(state);
			map.put(state, fly);
		}
		return fly;
	}

}
