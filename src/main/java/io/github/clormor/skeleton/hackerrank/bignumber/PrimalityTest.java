package io.github.clormor.skeleton.hackerrank.bignumber;

import java.math.BigInteger;

public class PrimalityTest {

    public String isPrime(BigInteger i) {
        if (i.isProbablePrime(100)) {
            return "prime";
        } else {
            return "not prime";
        }
    }
}
