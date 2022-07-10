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
        String operand = "(\\d+|\\d+\\.\\d+)" +
                "|[a-zA-Z$][\\w$]*|_[\\w$]+";
        String operators = "([\\/*+-])";
        String expression = String.format("(%s)(%s)",operand,operators);
        return String.format("(%s)+(%s)",expression,operand);
    }

}
