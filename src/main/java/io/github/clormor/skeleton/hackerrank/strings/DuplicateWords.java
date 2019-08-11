package io.github.clormor.skeleton.hackerrank.strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DuplicateWords {

    static final Pattern p = Pattern.compile("((?<!\\w)\\w+)(\\s+\\1(?!\\w))+", Pattern.CASE_INSENSITIVE);

    public String removeDuplicates(String s) {
        Matcher m = p.matcher(s);
        while (m.find()) {
            System.out.println(m.group(0));
            System.out.println(m.group(1));
            s = s.replaceAll(m.group(0), m.group(1));
        }
        return s;
    }
}
