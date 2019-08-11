package io.github.clormor.skeleton.hackerrank.bignumber;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;

public class BigDecimalSort {

    public String[] sort(String[] strings, int n) {
        Arrays.sort(strings, 0, n, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                BigDecimal b1 = new BigDecimal(s1);
                BigDecimal b2 = new BigDecimal(s2);
                return b2.compareTo(b1);
            }
        });
        return strings;
    }

    public String[] sort(String[] strings) {
        return sort(strings, strings.length);
    }
}
