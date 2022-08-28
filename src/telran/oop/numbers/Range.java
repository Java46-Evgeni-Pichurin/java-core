package telran.oop.numbers;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class Range implements Iterable<Integer> {
	
	//closed range
	private int min; //inclusive
	private int max; //inclusive 
	private Predicate<Integer> predicate;
	
	public Predicate<Integer> getPredicate() {
		return predicate;
	}
	public void setPredicate(Predicate<Integer> predicate) {
		this.predicate = predicate;
	}
	private class RangeIterator implements Iterator<Integer> {
		//If predicate == null all range numbers should be iterated
		Predicate<Integer> iteratorPredicate = getIteratorPredicate();
        Integer current = getFirst(); 
		@Override
		public boolean hasNext() {
			
			return current != null;
		}

		private Integer getFirst() {
			
			return iteratorPredicate.test(min) ? min : getNext(min);
		}

		private Predicate<Integer> getIteratorPredicate() {
			
			return predicate == null ? n -> true : predicate;
		}

		@Override
		public Integer next() {
			
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			Integer res = current;
			current = getNext(current);
			return res;
		}

		private Integer getNext(Integer from) {
			while (++from <= max && !iteratorPredicate.test(from)) {}
			return from > max ? null : from;
		}
		
	}
	public Range(int min, int max) {
		
		this.min = min;
		this.max = max;
	}
	@Override
	public Iterator<Integer> iterator() {
		
		return new RangeIterator();
	}
	public int length () {
		return max - min + 1;
	}

}
