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
            // move forward both
            return isSubstring(str.substring(1), substr.substring(1), initSubstr);
        }
        if (str.charAt(0) == initSubstr.charAt(0)) {
            // checking equality of the first char of initial substring with the first char of string
            // move forward both (substring from the initial form)
            return isSubstring(str.substring(1), initSubstr.substring(1), initSubstr);
        }
        if (str.charAt(0) == initSubstr.charAt(1)) {
            // checking equality of the second char of initial substring with the first char of string
            // move forward only string from the second char
            return isSubstring(str.substring(2), substr, initSubstr);
        }
        // move forward only string
        // substring comes back to initial form
        return isSubstring(str.substring(1), initSubstr, initSubstr);
    }
}
