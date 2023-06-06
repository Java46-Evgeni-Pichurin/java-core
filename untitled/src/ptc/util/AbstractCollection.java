package ptc.util;

public abstract class AbstractCollection<T> implements Collection<T>{
    int size;

    @Override
    public int size(){
        return size;
    }
}
