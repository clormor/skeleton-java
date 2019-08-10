package io.github.clormor.skeleton.hackerrank.bignumber;

import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class PrimalityTestTest {

    private PrimalityTest p;

    @Before
    public void init() {
        p = new PrimalityTest();
    }

    @Test
    public void simple_test() {
        BigInteger b = new BigInteger("13");
        assertEquals("prime", p.isPrime(b));
    }

    @Test
    public void big_number_test() {
        BigInteger b = new BigInteger("1313131313131313131313131313131313131313131313131313131313131313131313132");
        assertEquals("not prime", p.isPrime(b));
    }
}
