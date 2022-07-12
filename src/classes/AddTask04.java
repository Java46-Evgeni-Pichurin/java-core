package classes;

public class AddTask04 {
    private static final String brackets1 = "()";
    private static final String brackets2 = "{}";
    private static final String brackets3 = "[]";

    public static boolean hasaValidBrackets(String text) {
        text = text.replaceAll("[^(){}\\[\\]]", "");
        do {
            if (text.contains(brackets1)) {
                text = text.replace(brackets1, "");
            }
            if (text.contains(brackets2)) {
                text = text.replace(brackets2, "");
            }
            if (text.contains(brackets3)) {
                text = text.replace(brackets3, "");
            }
        } while (text.contains(brackets1) || text.contains(brackets2) || text.contains(brackets3));
        return text.isEmpty();
    }
}
