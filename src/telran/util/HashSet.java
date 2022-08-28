package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashSet<T> extends AbstractCollection<T>implements Set<T> {
private static final double DEFAULT_FACTOR = 0.75;
private static final int DEFAULT_HASH_TABLE_CAPACITY = 16;
private List<T> [] hashTable;
private double factor ;
@SuppressWarnings("unchecked")
public HashSet(int hashTableCapacity, double factor) {
	this.factor = factor;
	hashTable = new List[hashTableCapacity];
}
public HashSet(int hashTableCapacity) {
	this(hashTableCapacity, DEFAULT_FACTOR);
}
public HashSet() {
	this(DEFAULT_HASH_TABLE_CAPACITY, DEFAULT_FACTOR);
}

private class HashSetIterator implements Iterator<T> {
	Iterator<T> currentIterator;
	Iterator<T> prevIterator;
	int indexIterator = 0;
	boolean flNext = false;
	HashSetIterator() {

		getCurrentIterator();
	}
		@Override
		public boolean hasNext() {
			return currentIterator != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T res = currentIterator.next();
			prevIterator = currentIterator;
			getCurrentIterator();
			flNext = true;
			return res;
		}
		private void getCurrentIterator() {
			if (currentIterator == null || !currentIterator.hasNext()) {
				Iterator<T> it = null;
				while(it == null || !it.hasNext()) {
					List<T> list = getList();
					indexIterator++;
					if (list == null) {
						currentIterator = null;
						return;
					}
					it = list.iterator();
				}
				currentIterator = it;
			}
		}

		private List<T> getList() {
			while(indexIterator < hashTable.length &&
					hashTable[indexIterator] == null) {
				indexIterator++;
			}
			return indexIterator < hashTable.length ?
					hashTable[indexIterator] : null;
		}

		@Override
		public void remove() {
			if(!flNext) {
				throw new IllegalStateException();
			}
			prevIterator.remove();
			flNext = false;
			size--;
		}
}
	@Override
	public boolean add(T obj) {
		// set can not have two equal objects
		// that's why the method returns false at adding an object that already exists
		boolean res = false;
		if (!contains(obj)) {
			res = true;
			if (size >= hashTable.length * factor) {
				recreateHashTable();
			} 
			int hashTableInd = getHashTableIndex(obj.hashCode());
			if (hashTable[hashTableInd] == null) {
				hashTable[hashTableInd] = new LinkedList<T>();
			}
			hashTable[hashTableInd].add(obj);
			size++;
		}
		return res;
	}

	private void recreateHashTable() {
		HashSet<T> tmp = new HashSet<>(hashTable.length * 2); //tmp hashset has table with twice capacity
		for (List<T> list: hashTable) {
			if (list != null) {
				for(T obj: list) {
					tmp.add(obj);
				}
			}
		}
		hashTable = tmp.hashTable;
	}

	private int getHashTableIndex(int hashCode) {
		int res = Math.abs(hashCode) % hashTable.length;
		return res;
	}

	@Override
	public boolean remove(Object pattern) {
		int index = getHashTableIndex(pattern.hashCode());
		boolean res = false;
		if (hashTable[index] != null) {
			res = hashTable[index].remove(pattern);
			if (res) {
				size--;
			}
		}
		return res;
	}

	@Override
	public boolean contains(Object pattern) {
		int index = getHashTableIndex(pattern.hashCode());
		boolean res = false;
		if (hashTable[index] != null) {
			res = hashTable[index].contains(pattern);
		}
		return res;
	}

	

	@Override
	public Iterator<T> iterator() {
		
		return new HashSetIterator();
	}

}
