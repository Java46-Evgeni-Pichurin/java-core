package telran.text.comparators;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;

public class StringsComparator implements Comparator<String> {

    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public int compare(String o1, String o2) {
        try {
            return format.parse(o1).compareTo(format.parse(o2));
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
