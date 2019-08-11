package io.github.clormor.skeleton.hackerrank.strings;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestDuplicateWords {

    private DuplicateWords d;

    @Before
    public void setup() {
        d = new DuplicateWords();
    }

    @Test
    public void simple_test() {
        assertEquals("I love to code", d.removeDuplicates("I love love Love to To tO code"));
    }

    @Test
    public void sneaky_test() {
        assertEquals("Goodbye bye world", d.removeDuplicates("Goodbye bye bye world world world"));
    }

    @Test
    public void another_sneaky_test() {
        assertEquals("Hello Ab", d.removeDuplicates("Hello hello Ab aB"));
    }

    @Test
    public void another_very_sneaky_test() {
        assertEquals("in inthe", d.removeDuplicates("in inthe"));
    }
}
