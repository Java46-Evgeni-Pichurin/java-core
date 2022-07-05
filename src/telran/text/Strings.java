package telran.text;

import java.util.Arrays;
public class Strings {
    private static final int NUMBER_OF_OPTIONS = 1000;

    static public String matches(String name1, String name2) {
        String matches = "match";
        String noMatches = "no match";
        String res = noMatches;
        name1 = name1.toLowerCase();
        name2 = name2.toLowerCase();
        String delimiter = " ";
        String[] stringsOfName1 = name1.split(delimiter);
        String[] stringsOfName2 = name2.split(delimiter);
        for (int i = 0; i < stringsOfName1.length; i++) {
            for (int j = 0; j < stringsOfName2.length; j++) {
                if (!stringsOfName1[i].equals(stringsOfName2[j])) {
                    if (j > 0 && i == j) {
                        if ((stringsOfName1[i].length() == 1 || stringsOfName2[j].length() == 1)
                                && stringsOfName1[i].charAt(0) == stringsOfName2[j].charAt(0)) {
                            res = matches;
                        } else res = noMatches;
                    }
                } else res = matches;
            }
        }
        return res;
    }

    static public void sortStringsAsNumbers(String[] strNumbers) {
        int[] numbers = new int[NUMBER_OF_OPTIONS];
        for (int i = 0; i < strNumbers.length; i++) {
            numbers[Integer.parseInt(strNumbers[i].trim())]++ ;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            while (numbers[i] > 0) {
                builder.append(i).append(", ");
                numbers[i]--;
            }
        }
        String res = builder.toString();
        System.out.println("Before sorting = " + Arrays.toString(strNumbers));
        System.out.println("After sorting = " + Arrays.toString(new String[]{res.substring(0, res.length() - 2)}));
    }
}