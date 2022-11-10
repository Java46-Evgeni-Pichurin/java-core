package telran.view.tests;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import telran.view.ConsoleInputOutput;
import telran.view.InputOutput;

class InputOutputTest {
    private static final String ERROR = "ERROR\n";
    InputOutput io = new ConsoleInputOutput();

    @Test
    void readObjectTest() {
        Integer[] array = io.readObject("Enter numbers separated by space",
                ERROR, s -> {

                    String[] strNumbers = s.split(" ");
                    return Arrays.stream(strNumbers).map(Integer::parseInt)
                            .toArray(Integer[]::new);

                });
        io.writeLine(Arrays.stream(array).collect(Collectors.summarizingInt(x -> x)));
    }

    @Test
    void readIntMinMax() {
        Integer res = io.readInt("Enter any number in range [1, 40]", ERROR, 1, 40);
        io.writeLine(res);
    }

    @Test
    void readOptionTest() {
        List<String> list = new ArrayList<>(List.of("BMW", "Mazda", "KIA", "Mercedes", "Ford"));
        String res = io.readOption("Choose your car from the list: " + list, ERROR, list);
        io.writeLine(res);
    }

    @Test
    void readDateTest() {
        LocalDate res = io.readDate("Put a date in format yyyy-MM-dd.", ERROR);
        io.writeLine(res);
    }

    @Test
    void readDateFormatTest() {
        LocalDate res = io.readDate("Put a date in format dd/MM/yyyy. ", ERROR, "dd/MM/yyyy");
        io.writeLine(res);
    }

    @Test
    void readPredicateTest() {
        String res = io.readPredicate("Put a string with length 10.", ERROR, s -> s.length() == 10);
        io.writeLine(res);
    }

}