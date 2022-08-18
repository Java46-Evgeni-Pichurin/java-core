package telran.recursion;

public class Substring {
    /**
     * @param str    string
     * @param substr substring
     * @return true if a given 'substr' is indeed the substring of a given string
     * Challenges: 1. To apply only following methods of the class String:
     * charAt(int ind); String substring(int ind); int length(); 2. No cycles;
     */
    public static boolean isSubstring(String str, String substr) {
        return isSubstring(str, substr, substr);
    }

    private static boolean isSubstring(String str, String substr, String initSubstr) {
        if (str.length() < substr.length()) {
            return false;
        }
        if (substr.length() == 0) {
            return true;
        }
        if (str.charAt(0) == substr.charAt(0)) {
            return isSubstring(str.substring(1), substr.substring(1), substr = initSubstr);
        }
        substr = initSubstr;
        if (str.charAt(0) == substr.charAt(0)) {
            return isSubstring(str.substring(1), substr.substring(1), substr);
        }
        return isSubstring(str.substring(1), substr, substr);
    }
}
