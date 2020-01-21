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
        assertThat(newFizzBuzz.fizzBuzz()).containsExactlyElementsOf(legacyResult);
    }

    @Test
    public void testVariousWorkingImplementationsInJava8() {
        FizzBuzzJava8 fizzBuzz = (FizzBuzzJava8) newFizzBuzz;

        // ignore attempt1 which is known to be incorrect
        List<String> attempt2 = fizzBuzz.fizzBuzzAttempt2();

        assertThat(fizzBuzz.fizzBuzzAttempt3()).containsExactlyElementsOf(attempt2);
        assertThat(fizzBuzz.fizzBuzzAttempt4()).containsExactlyElementsOf(attempt2);
        assertThat(fizzBuzz.fizzBuzzAttempt5()).containsExactlyElementsOf(attempt2);
        assertThat(fizzBuzz.fizzBuzzAttempt6()).containsExactlyElementsOf(attempt2);
    }
}
