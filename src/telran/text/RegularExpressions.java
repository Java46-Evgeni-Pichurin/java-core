package telran.text;

public class RegularExpressions {

    public static String javaVariableRegex() {

        return "[a-zA-Z$][\\w$]*|_[\\w$]+";
    }

    private static String ipOctetRegex() {
        return "\\d\\d?|[0,1]\\d\\d|2[0-4]\\d|25[0-5]";
    }

    public static String ipV4Regex() {
        return String.format("((%s)\\.){3}(%s)", ipOctetRegex(), ipOctetRegex());
    }

    public static String arithmeticExpression() {
        return  "[1-9]\\d*[\\/*+-][1-9]+\\d*" +
                "|[1-9]\\d*[\\/*+-][a-zA-Z$][\\w$]+" +
                "|[1-9]\\d*[\\/*+-]_[\\w$]+" +
                "|[1-9]\\d*[\\/*+-].+" +
                "|[a-zA-Z$][\\w$]*[\\/*+-][1-9]+\\d*" +
                "|[a-zA-Z$][\\w$]*[\\/*+-][a-zA-Z$][\\w$]+" +
                "|[a-zA-Z$][\\w$]*[\\/*+-]_[\\w$]+" +
                "|[a-zA-Z$][\\w$]*[\\/*+-].+" +
                "|_[\\w$]+[\\/*+-][1-9]+\\d*" +
                "|_[\\w$]+[\\/*+-][a-zA-Z$][\\w$]+" +
                "|_[\\w$]+[\\/*+-]_[\\w$]+" +
                "|_[\\w$]+[\\/*+-].+";
    }

}
