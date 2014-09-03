package designpattern.singleton;

/**
 * 另外一种单例的写法
 * @author LeeFranker
 *
 */
public class Singleton_1 {

	private static Singleton_1 instance = null;

	private Singleton_1() {

	}

	private static synchronized void syncInit() {
		if (instance == null)
			instance = new Singleton_1();

	}

	public static Singleton_1 getInstance() {
		if (instance == null)
			syncInit();
		return instance;
	}
}
