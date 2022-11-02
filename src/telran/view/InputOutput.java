package telran.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public interface InputOutput {
    String readString(String prompt);

    void writeObject(Object obj);

    default void close() {
    }

    default void writeLine(Object obj) {
        String str = obj + "\n";
        writeObject(str);
    }

    default <R> R readObject(String prompt, String errorPrompt, Function<String, R> mapper) {
        R result;
        while (true) {
            String str = readString(prompt);
            try {
                result = mapper.apply(str);
                break;
            } catch (Exception e) {
                writeLine(errorPrompt + e.getMessage());
            }
        }
        return result;

    }

    default Integer readInt(String prompt, String errorPrompt) {
        return readObject(prompt, errorPrompt, Integer::parseInt);
    }

    default Integer readInt(String prompt, String errorPrompt, int min, int max) {
        return readObject(prompt, errorPrompt, s -> {
            int num = Integer.parseInt(s);
            if (num < min || num > max) {
                throw new RuntimeException(String.format("%d out of the range - [%d - %d]", num, min, max));
            }
            return num;

        });
    }

    default Long readLong(String prompt, String errorPrompt) {
        return readObject(prompt, errorPrompt, Long::parseLong);
    }

    default String readOption(String prompt, String errorPrompt, List<String> options) {
        return readObject(prompt, errorPrompt, option -> {
            if (!options.contains(option)) {
                throw new RuntimeException(String.format("There is no such option in the list: %s", options));
            }
            return option;
        });
    }

    default LocalDate readDate(String prompt, String errorPrompt) {
        return readObject(prompt, errorPrompt, stringDate ->
                getLocalDate(stringDate ,"YYYY-MM-DD"));
    }

    default LocalDate readDate(String prompt, String errorPrompt, String format) {
        return readObject(prompt, errorPrompt, stringDate -> getLocalDate(stringDate, format));
    }

    default LocalDate getLocalDate(String stringDate, String format) {
        LocalDate res;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            res = LocalDate.parse(stringDate, formatter);
        } catch (Exception e) {
            throw new RuntimeException("Invalid input date format.");
        }
        return res;
    }

    default String readPredicate(String prompt, String errorPrompt, Predicate<String> predicate) {
        return readObject(prompt, errorPrompt, s -> predicate.test(s) ? s : "Given data does not pass the test." );
    }
}