package telran.numbers;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class Range implements Iterable<Integer> {
	
	//closed range
	private int min; //inclusive
	private int max; //inclusive 
	private Predicate<Integer> predicate;

	public Range(int min, int max) {

		this.min = min;
		this.max = max;
	}
	
	public Predicate<Integer> getPredicate() {
		return predicate;
	}
	public void setPredicate(Predicate<Integer> predicate) {
		this.predicate = predicate;
	}
	@Override
	public Iterator<Integer> iterator() {

		return new RangeIterator();
	}
	public int length () {
		return max - min + 1;
	}
	private class RangeIterator implements Iterator<Integer> {
		int current = findCurOrMax(min, true);
		int iteratorMax = findCurOrMax(max, false);

		@Override
		public boolean hasNext() {
			return current <= iteratorMax;
		}

		@Override
		public Integer next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			current = findCurOrMax(current, true);
			return findCurOrMax(current++, true);
		}
		private int findCurOrMax(int start, boolean goUp) {
			if (predicate == null) {
				return start;
			}
			int next = goUp ? start + 1 : start - 1;
			return predicate.test(start) ? start : findCurOrMax(next, goUp);
		}
	}
}