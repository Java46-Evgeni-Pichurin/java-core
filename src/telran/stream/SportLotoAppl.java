package telran.stream;

import java.util.Arrays;
import java.util.Random;

public class SportLotoAppl {
    final static int N_NUMBERS = 7;
    final static int MIN = 1;
    final static int MAX = 49;

    public static void main(String[] args) {
        new Random().ints(MIN, MAX + 1).distinct()
                .limit(N_NUMBERS)
                .forEach(n -> System.out.print(n + " "));
    }
}
