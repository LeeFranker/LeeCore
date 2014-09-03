package designpattern.iterator;

public class Test {
	public void test(){
		IfaceCollection collection=new Collection();
		IfaceIterator iterator=collection.iterator();
		while(iterator.haveNext())
			iterator.next();
	}
}
