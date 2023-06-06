package ptc.util;

public interface Collection<T> extends Iterable<T> {
    boolean add(T object);
    boolean remove(Object pattern);
    boolean contains(Object pattern);
    int size();
}
