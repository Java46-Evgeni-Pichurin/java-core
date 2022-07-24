package telran;

import java.util.Arrays;

public class Strings {

    public static String[] sortStringsAsDates(String [] dates) {
        String[] res = Arrays.copyOf(dates, dates.length);
        Arrays.sort(res, new StringsComparator());
        return res;
    }
}