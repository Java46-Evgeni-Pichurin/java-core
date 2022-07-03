package telran.text;

public class Strings {

    private static final int LAST_INDEX_OF_ASCII = 122;
    private static final int FIRST_INDEX_OF_ASCII = 97;

    public static int[] deepNoRepeatedCompare(String str1, String str2) {
        int[] arrayRes = {0, 0};
        char[] arrayOfSymbolsFromFirstParam = new char[LAST_INDEX_OF_ASCII - FIRST_INDEX_OF_ASCII + 1];
        for (int i = 0; i < str1.length(); i++) {
            arrayOfSymbolsFromFirstParam[str1.charAt(i) - FIRST_INDEX_OF_ASCII] = str1.charAt(i);
        }
        for (int i = 0; i < str2.length(); i++) {
            if (i < str1.length() && str1.charAt(i) == str2.charAt(i)) {
                arrayRes[0]++;
            } else if (arrayOfSymbolsFromFirstParam[str2.charAt(i) - FIRST_INDEX_OF_ASCII] == str2.charAt(i)) {
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
        int[] resArr = deepNoRepeatedCompare(str1, str2);
        return resArr[0] + resArr[1] == str1.length();
    }
}