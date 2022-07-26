package telran.numbers;

import java.util.function.Predicate;

public class OddNumbersPredicate implements Predicate<Integer> {

    @Override
    public boolean test(Integer t) {
        return Math.abs(t % 2) == 1;
    }
}
