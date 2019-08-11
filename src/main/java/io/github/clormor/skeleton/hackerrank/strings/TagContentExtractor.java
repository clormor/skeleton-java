package io.github.clormor.skeleton.hackerrank.strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagContentExtractor {

    /*
     * <          - look for tag start
     * (?!/)      - make sure this is not a closing tag
     * (.+)>      - greedily (i.e. largest) match of one or more
     *              printable characters followed by tag end.
     *              captures the contents as group 1
     * ([^<>]+)   - greedily (i.e. largest) match content within tag.
     *              can contain any printable character except angle brackets.
     *              captures the contents as group 2
     * </\\1>     - find a closing tag whose name matches the contents
     *              of group 1 (i.e. a matching closing tag)
     */
    static final Pattern p = Pattern.compile("<(?!/)(.+)>([^<>]+)</\\1>");

    public String extract(String[] lines) {
        StringBuilder result = new StringBuilder();
        for (String line : lines) {
            result.append(extract(line));
        }
        return result.toString();
    }

    public String extract(String line) {
        Matcher m = p.matcher(line);
        StringBuilder content = new StringBuilder();
        while (m.find()) {
            content.append(m.group(2)).append(System.lineSeparator());
        }
        if (content.length() == 0) {
            content.append("None").append(System.lineSeparator());
        }
        return content.toString();
    }
}
