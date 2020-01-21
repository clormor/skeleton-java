package io.github.clormor.skeleton.java8;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class FizzBuzzTest {

    FizzBuzz oldFizzBuzz;
    FizzBuzz newFizzBuzz;

    @Before
    public void setup() {
        oldFizzBuzz = new FizzBuzzLegacy();
        newFizzBuzz = new FizzBuzzJava8();
    }

    @Test
    public void legacyAndJava8ReturnIdenticalResults() {
        List<String> legacyResult = oldFizzBuzz.fizzBuzz();
        List<String> java8Result = newFizzBuzz.fizzBuzz();
        assertThat(java8Result).containsExactlyElementsOf(legacyResult);
    }

    @Test
    public void testVariousWorkingImplementationsInJava8() {
        FizzBuzzJava8 fizzBuzz = (FizzBuzzJava8) newFizzBuzz;

        // ignore attempt1 which is known to be incorrect
        List<String> attempt2 = fizzBuzz.fizzBuzzAttempt2();
        List<String> attempt3 = fizzBuzz.fizzBuzzAttempt3();
        List<String> attempt4 = fizzBuzz.fizzBuzzAttempt4();

        assertThat(attempt3).containsExactlyElementsOf(attempt2);
        assertThat(attempt4).containsExactlyElementsOf(attempt2);
    }
}
