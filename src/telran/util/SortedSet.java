package telran.util;

public interface SortedSet<T> extends Set<T> {
	/**
	 * 
	 * @return reference to the least object
	 */
	T first();

	/**
	 * 
	 * @return reference to the most object
	 */
	T last();
	
	/**
	 * 
	 * @param pattern
	 * @return Returns the least element in this set
	 *  greater than or equal to the given element,
	 *   or null if there is no such element.
	 */
	T ceiling(T pattern) ;
	/**
	 * 
	 * @param pattern
	 * @return Returns the greatest element in this set
	 *  less than or equal to the given element,
	 *   or null if there is no such element.
	 */
	T floor(T pattern) ;
}
