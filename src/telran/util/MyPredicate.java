package telran.util;

import java.util.Objects;

public interface MyPredicate<T> {

    default MyPredicate<T> and(MyPredicate<T> other) {
        return t -> other.test(t) && this.test(t);
    }

    static <T> MyPredicate<T> isEqual(Object targetRef){
        return t -> Objects.equals(t, targetRef);
    }

    default MyPredicate<T> negate(){
        return t-> !test(t);
    }

    static <T> MyPredicate<T> not(MyPredicate<T> target){
        return target.negate();
    }

    default MyPredicate<T> or(MyPredicate<T> other) {
        return t ->  other.test(t) || this.test(t);
    }

    boolean test(T t);
}
