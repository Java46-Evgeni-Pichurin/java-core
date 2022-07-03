package telran.text;

import java.util.Arrays;

public class Strings {

    private static final int LAST_INDEX_OF_ALPHABET_FROM_ASCII = 123;

    public static int[] deepNoRepeatedCompare(String str1, String str2) {
        int[] arrayRes = {0, 0};
        char[] arrayOfSymbolsFromFirstParam = str1.toCharArray();
        for (int i = 0; i < str2.length(); i++) {
            if (i < str1.length() && arrayOfSymbolsFromFirstParam[i] == str2.charAt(i)) {
                arrayRes[0]++;
            } else if (str1.indexOf(str2.charAt(i)) >= 0) {
                arrayRes[1]++;
            }
        }
        return arrayRes;
    }

    public static boolean isAnagram(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        int[] helperArrForStr1 = new int[LAST_INDEX_OF_ALPHABET_FROM_ASCII];
        int[] helperArrForStr2 = new int[LAST_INDEX_OF_ALPHABET_FROM_ASCII];
        for (int i = 0; i < str1.length(); i++) {
            helperArrForStr1[str1.charAt(i)]++;
            helperArrForStr2[str2.charAt(i)]++;
        }
        return Arrays.equals(helperArrForStr1, helperArrForStr2);
    }
}