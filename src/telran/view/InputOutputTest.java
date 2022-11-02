package telran.view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class InputOutputTest {
    private static final String WRONG_OPTION = "Wrong option. ";
    InputOutput io = new ConsoleInputOutput();

    @Test
    @Disabled
    void readObjectTest() {
        Integer[] array = io.readObject("Enter numbers separated by space",
                "no number ", s -> {
                    String[] strNumbers = s.split(" ");
                    return Arrays.stream(strNumbers).map(Integer::parseInt)
                            .toArray(Integer[]::new);
                });
        io.writeLine(Arrays.stream(array).collect(Collectors.summarizingInt(x -> x)));
    }

    @Test
    void readIntMinMax() {
        Integer res = io.readInt("Enter any number in range [1, 40]", WRONG_OPTION, 1, 40);
        io.writeLine(res);
    }

    @Test
    void readOptionTest() {
        List<String> list = new ArrayList<>(List.of("BMW", "Mazda", "KIA", "Mercedes", "Ford"));
        String res = io.readOption("Choose your car from the given list", WRONG_OPTION, list);
        io.writeLine(res);
    }

    @Test
    void readDateTest() {
        LocalDate res = io.readDate("Put a date", WRONG_OPTION);
        io.writeLine(res);
    }

    @Test
    void readDateFormatTest() {
        LocalDate res = io.readDate("Put a date", WRONG_OPTION, "yyyy");
        io.writeLine(res);
    }

    @Test
    void readPredicateTest() {
        String res = io.readPredicate("Put a string", WRONG_OPTION, s -> s.length() == 10);
        io.writeLine(res);
    }
}
