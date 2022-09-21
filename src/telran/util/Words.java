package telran.util;

import java.util.*;
import java.util.stream.Collectors;

public class Words {
    LinkedHashSet<String> words;
    LinkedHashSet<String> caseInsensitiveWords;

    public Words() {
        words = new LinkedHashSet<>();
        caseInsensitiveWords = new LinkedHashSet<>();
    }

    public boolean addWord(String word) {
        boolean isExists = caseInsensitiveWords.contains(word.toUpperCase());
        if (!isExists) {
            caseInsensitiveWords.add(word.toUpperCase());
            words.add(word);
        }
        return !isExists;
    }

    public List<String> getWordsByPrefix(String prefix) {
        if (prefix.length() > 0) {
            return words.stream().filter(s -> s.toUpperCase().startsWith(prefix.toUpperCase())).collect(Collectors.toList());
        }
        else {
            return words.stream().toList();
        }
    }
}