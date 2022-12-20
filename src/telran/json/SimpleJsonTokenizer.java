package telran.json;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This tokenizer is used by Json parser.
 * The supposed format of all JSON values are quoted strings.
 * The tokenizer breaks JSON string to following tokens:
 * - single bracket 	{ or } or [ or ]
 * - single colon 		:
 * - single comma 		,
 * - single word		any double-quoted sequence of letters (with escaped nested double quotes)
 */
class SimpleJsonTokenizer implements Iterator<String> {
    String text;
    int pos = 0;
    Matcher matcher;

    public SimpleJsonTokenizer(String text) {
        this.text = text;
        String singleBracketColonOrComma = "[\\{\\}\\[\\]:,]";        // regex [\{\}\[\]:,]
//		String quotedStringPattern = "\"[^\"]*\""; 					// regex "[^"]*" 		// does not cover sequence \"
        String quotedStringPattern = "\".*?(?<!\\\\)\"";            // regex ".*?(?<!\\)"	// covers sequence \"
        Pattern pattern = Pattern.compile("\\s*(" + singleBracketColonOrComma + "|" + quotedStringPattern + ")\\s*"); // token with blanks

        matcher = pattern.matcher(text);
    }

    @Override
    public boolean hasNext() {
        return matcher.find(pos);
    }

    @Override
    public String next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        String res = matcher.group(1);
        pos += matcher.group().length();
        return res;
    }

    // returns future token without advancing the iterator
    public String peek() {
        if (!hasNext()) {
            return null;
        }
        return matcher.group(1);
    }

    // tokenizer test
    public static void main(String[] args) {
        String json1 = "{\"name\":\"John\\\"\",\"age\":\"23\",\"address\":\"Haifa Herzel 12\",\"salary\":\"100.00\",\"bday\":\"10/10/1991\"}";
        String json2 = "{\"name\":\"John\", \"age\":\"23\",\"address\":{\"city\":\"Haifa\",\"street\":\"Herzel\",\"house\":\"12\"},\"salary\":\"100.00\",\"bday\":\"10/10/1991\"}";
        String json3 = "[" + json1 + "," + json2 + "]";
        SimpleJsonTokenizer tokenizer = new SimpleJsonTokenizer(json3); // json1 or json2
        while (tokenizer.hasNext()) {
            System.out.println(tokenizer.next());
        }
    }
}
