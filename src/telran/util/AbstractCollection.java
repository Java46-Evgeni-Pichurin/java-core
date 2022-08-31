package telran.util;


public abstract class AbstractCollection<T> implements Collection<T> {
	protected int size;

	
	@Override
	public int size() {
		
		return size;
	}

}
