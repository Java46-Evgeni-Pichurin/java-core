package telran.text;

import static telran.text.RegularExpressions.*;

public class Strings {

    public static boolean isIPv4(String ipV4str) {
        return ipV4str.matches(ipV4Regex());
    }

    public static boolean isJavaVariable(String javaVariable) {
        return javaVariable.matches(javaVariableRegex());
    }

    public static boolean isArithmeticExpression(String expression) {
        if (!checkParentheses(expression)) {
            return false;
        }
        expression = removeSpacesAndParentheses(expression);;
        System.out.println(expression);
        return expression.matches(arithmeticExpression());
    }

    private static String removeSpacesAndParentheses(String expression) {
        expression = expression.replaceAll("[\\[\\]{}() ]", "");
        return expression;
    }

    private static boolean checkParentheses(String expression) {
        expression = expression.replaceAll("[^()]", "");
        while (expression.contains("()")) {
            expression = expression.replace("()", "");
        }
        return expression.isEmpty();
    }
}
