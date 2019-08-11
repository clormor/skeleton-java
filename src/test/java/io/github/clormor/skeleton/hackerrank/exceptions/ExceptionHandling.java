package io.github.clormor.skeleton.hackerrank.exceptions;

public class ExceptionHandling {

    static final String zero_error = "n and p should not be zero.";
    static final String negative_error = "n or p should not be negative.";

    public long power(int n, int p) throws Exception {
        if (n == 0 && p == 0) throw new Exception(zero_error);
        if (n < 0 || p < 0) throw new Exception(negative_error);
        return (long) Math.pow(n, p);
    }
}
