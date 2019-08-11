package io.github.clormor.skeleton.hackerrank.bignumber;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestBigDecimalSort {

    BigDecimalSort s;

    @Before
    public void setup() {
        s = new BigDecimalSort();
    }

    @Test
    public void simple_test() {
        String[] result = s.sort(new String[] {
                "0.12",
                "-3"
        });
        assertEquals("0.12", result[0]);
        assertEquals("-3", result[1]);
    }

    @Test
    public void leading_dot_test() {
        String[] result = s.sort(new String[] {
                ".12",
                "-3"
        });
        assertEquals(".12", result[0]);
        assertEquals("-3", result[1]);
    }

    @Test
    public void hacker_rank_test() {
        String[] result = s.sort(new String[] {
                "-100",
                "50",
                "0",
                "56.6",
                "90",
                "0.12",
                ".12",
                "02.34",
                "000.000",
                null,
                null
        },9);
        assertEquals("90", result[0]);
        assertEquals("56.6", result[1]);
        assertEquals("50", result[2]);
        assertEquals("02.34", result[3]);
        assertEquals("0.12", result[4]);
        assertEquals(".12", result[5]);
        assertEquals("0", result[6]);
        assertEquals("000.000", result[7]);
        assertEquals("-100", result[8]);
    }
}
