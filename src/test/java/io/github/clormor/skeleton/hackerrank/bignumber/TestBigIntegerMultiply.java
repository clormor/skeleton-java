package io.github.clormor.skeleton.hackerrank.bignumber;

import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class TestBigIntegerMultiply {

    BigIntegerMultiply b;

    @Before
    public void init() {
        b = new BigIntegerMultiply();
    }

    @Test
    public void multiply_two_small_numbers() {
        BigInteger b1 = new BigInteger("13");
        BigInteger b2 = new BigInteger("10");
        BigInteger result = b.multiply(b1,b2);
        assertEquals("130", result.toString());
    }

    @Test
    public void add_two_small_numbers() {
        BigInteger b1 = new BigInteger("13");
        BigInteger b2 = new BigInteger("10");
        BigInteger result = b.add(b1,b2);
        assertEquals("23", result.toString());
    }

    @Test
    public void multiply_two_large_numbers() {
        BigInteger b1 = new BigInteger("1387292390842798327423675632532235675325235325");
        BigInteger b2 = new BigInteger("3276373276327868763276326486787643267432");
        BigInteger result = b.multiply(b1,b2);
        assertEquals(
                "4545287715810341397516715219700703233551634887354197640381121622861363400667308435400",
                result.toString()
        );
    }

    @Test
    public void add_two_large_numbers() {
        BigInteger b1 = new BigInteger("1387292390842798327423675632532235675325235325");
        BigInteger b2 = new BigInteger("3276373276327868763276326486787643267432");
        BigInteger result = b.add(b1,b2);
        assertEquals(
                "1387295667216074655292438908858722462968502757",
                result.toString()
        );
    }
}
