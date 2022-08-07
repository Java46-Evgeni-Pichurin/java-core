package telran.util;

public interface List<T> extends Collection<T> {
	/**
	 * insert object at the given index
	 * 
	 * @param index
	 * @param obj
	 * @return true if index >=0 && index <=size
	 */
	boolean add(int index, T obj);

	/*************************************************************/
	/**
	 * 
	 * @param index
	 * @return reference to being removed object (if index >=0 && index < size) or null (otherwise)
	 */
	T remove(int index);
	/***************************************************************/
/**
 * 
 * @param pattern
 * @return index of the first object equaled to the given pattern
 */
	int indexOf(Object pattern);
	/******************************************************/
	/**
	 * 
	 * @param pattern
	 * @return index of the last object equaled to the given pattern
	 */
	int lastIndexOf(Object pattern);
	/***************************************************************/
	/**
	 * 
	 * @param index
	 * @return the reference to an object at the given index for index [0, size - 1] or null for wrong index value 
	 */
	T get(int index);
	@Override
	default boolean contains(Object pattern) {
		return indexOf(pattern) >= 0;
	}

}
