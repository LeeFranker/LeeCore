package designpattern.singleton;

//JVM内部机制保证当一个类被加载这个类的加载过程是线程互斥的
public class Singleton {

	// 私有构造方法，防止被实例化
	private Singleton() {

	}

	// 此处使用一个内部类来维护单例
	private static class SingletonFactory {
		private static Singleton instance = new Singleton();
	}

	// 获取实例
	public static Singleton getInstance() {
		return SingletonFactory.instance;
	}

	/**
	 * 我们知道java 对象的序列化操作是实现Serializable接口，我们就可以把它往内存地写再从内存里读出而"组装"成一个跟原来一模一样的对象.
	 * 但是当我们遇到单例序列化的时候
	 * ，就出现问题了。当从内存读出而组装的对象破坏了单例的规则，会创建新的对象。单例要求JVM只有一个对象，而通过反序化时就会产生一个克隆的对象
	 * ，这就打破了单例的规则。
	 * 
	 * @return
	 */
	public Object readResolve() {
		return getInstance();
	}

}
