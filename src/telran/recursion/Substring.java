package telran.recursion;

public class Substring {
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
            return isSubstring(str.substring(1), substr.substring(1), initSubstr);
        }
        String tmp = substr;
        substr = initSubstr;
        if (str.charAt(0) == substr.charAt(0)) {
            return isSubstring(str.substring(1), substr.substring(1), initSubstr);
        }
        if (str.charAt(0) == substr.charAt(1)) {
            return isSubstring(str.substring(2), tmp, initSubstr);
        }
        return isSubstring(str.substring(1), substr, initSubstr);
    }
}