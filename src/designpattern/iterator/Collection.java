package designpattern.iterator;

public class Collection implements IfaceCollection{

	public String string[]={"A","B","C"};
	
	@Override
	public IfaceIterator iterator() {
		return new IteratorImpl(this);
	}

	@Override
	public Object get(int i) {
		return string[i];
	}

	@Override
	public int size() {
		return string.length;
	}

}
