package telran.util;

import java.util.Iterator;

public class StandardTreeSet<T> implements SortedSet<T> {
private java.util.TreeSet<T> treeSet = new java.util.TreeSet<>();
	@Override
	public boolean add(T obj) {
		
		 return treeSet.add(obj);
	}

	@Override
	public boolean remove(Object pattern) {
		
		return treeSet.remove(pattern);
	}

	@Override
	public boolean contains(Object pattern) {
		
		return treeSet.contains(pattern);
	}

	@Override
	public int size() {
		
		return treeSet.size();
	}

	@Override
	public Iterator<T> iterator() {
		
		return treeSet.iterator();
	}

	@Override
	public T first() {
		
		return treeSet.first();
	}

	@Override
	public T last() {
		
		return treeSet.last();
	}

	@Override
	public T ceiling(T pattern) {
		
		return treeSet.ceiling(pattern);
	}

	@Override
	public T floor(T pattern) {
		
		return treeSet.floor(pattern);
	}

}
