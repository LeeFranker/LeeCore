package designpattern.iterator;

public class IteratorImpl implements IfaceIterator {

	public IfaceCollection collection;
	private int postion = -1;

	public IteratorImpl(IfaceCollection collection) {
		this.collection = collection;
	}

	@Override
	public Object previous() {
		if (postion > 0)
			postion--;
		return collection.get(postion);
	}

	@Override
	public Object first() {
		postion = 0;
		return collection.get(postion);
	}

	@Override
	public boolean haveNext() {
		if (postion < collection.size() - 1)
			return true;
		else
			return false;
	}

	@Override
	public Object next() {
		if (postion < collection.size() - 1)
			postion++;
		return collection.get(postion);
	}

}
