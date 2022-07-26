package telran.numbers;

import java.util.function.Predicate;

public class EvenNumbersPredicate implements Predicate<Integer> {

    @Override
    public boolean test(Integer t) {
        return Math.abs(t % 2) == 0;
    }
}