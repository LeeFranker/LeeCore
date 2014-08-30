package designpattern.singleton;

/**
 * 另外一种单例的写法
 * @author LeeFranker
 *
 */
public class SingletonTest {

	private static SingletonTest instance = null;

	private SingletonTest() {

	}

	private static synchronized void syncInit() {
		if (instance == null)
			instance = new SingletonTest();

	}

	public static SingletonTest getInstance() {
		if (instance == null)
			syncInit();
		return instance;
	}
}