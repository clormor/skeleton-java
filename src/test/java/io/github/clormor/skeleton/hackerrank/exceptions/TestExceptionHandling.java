package io.github.clormor.skeleton.hackerrank.exceptions;

import org.junit.Before;
import org.junit.Test;

import static io.github.clormor.skeleton.hackerrank.exceptions.ExceptionHandling.negative_error;
import static io.github.clormor.skeleton.hackerrank.exceptions.ExceptionHandling.zero_error;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestExceptionHandling {

    ExceptionHandling e;

    @Before
    public void setup() {
        e = new ExceptionHandling();
    }

    @Test
    public void simple_hacker_rank_test() throws Exception {
        assertEquals(243L,e.power(3,5));
        assertEquals(16L,e.power(2,4));
    }

    @Test
    public void exceptions_hacker_rank_test() {
        try {
            e.power(0, 0);
            fail("An exception should have been thrown");
        } catch (Exception e) {
            assertEquals(zero_error, e.getMessage());
        }

        try {
            e.power(-1, -2);
            fail("An exception should have been thrown");
        } catch (Exception e) {
            assertEquals(negative_error, e.getMessage());
        }

        try {
            e.power(-1, 3);
            fail("An exception should have been thrown");
        } catch (Exception e) {
            assertEquals(negative_error, e.getMessage());
        }
    }
}
