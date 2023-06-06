package ptc.util;

public interface List<T> extends Collection<T>{
    boolean add(int index, T obj);
    T remove(int index);
    T get(int index);
    default int indexOf(Object pattern){
        int index = 0;
        int res = -1;
        for(T obj : this){
            if(obj.equals(pattern)){
                res = index;
                break;
            }
            index++;
        }
        return res;
    }
    @Override
    default boolean contains(Object obj){
        return indexOf(obj) >= 0;
    }

    default boolean checkingExistingIndex(int index){
        return index >= 0 && index < size();
    }

}
