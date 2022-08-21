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
            // moving forward both from the second char
            // substring keeps current form
            return isSubstring(str.substring(1), substr.substring(1), initSubstr);
        }
        if (str.charAt(0) == initSubstr.charAt(0)) {
            // checking equality of the first char of initial substring with the first char of string
            // moving forward both, from the second char (substring from the initial form)
            return isSubstring(str.substring(1), initSubstr.substring(1), initSubstr);
        }
        if (str.charAt(0) == initSubstr.charAt(1)) {
            // checking equality of the second char of initial substring with the first char of string
            // moving forward only for string from the third char
            // substring keeps current form
            return isSubstring(str.substring(2), substr, initSubstr);
        }
        // moving forward only for string from the second char
        // substring comes back to initial form
        return isSubstring(str.substring(1), initSubstr, initSubstr);
    }
}
