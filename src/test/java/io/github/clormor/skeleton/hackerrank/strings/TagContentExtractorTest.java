package io.github.clormor.skeleton.hackerrank.strings;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TagContentExtractorTest {

    TagContentExtractor t;

    @Before
    public void init() {
        t = new TagContentExtractor();
    }

    @Test
    public void test_a_thing() {
        String result = t.extract("</h>hello<//h>");
        assertEquals("None"+System.lineSeparator(), result);
    }


    @Test
    public void test_annoying_bug() {
        String result = t.extract("<i>test1</i><j>test2</j>");
        StringBuilder answers = new StringBuilder()
                .append("test1")
                .append(System.lineSeparator())
                .append("test2")
                .append(System.lineSeparator());
        assertEquals(answers.toString(), result);
    }

    @Test
    public void test_bug() {
        String result = t.extract("<h1><h1>Sanjay has no watch</h1></h1><par>So wait for a while<par>");
        StringBuilder answers = new StringBuilder()
                .append("Sanjay has no watch")
                .append(System.lineSeparator());
        assertEquals(answers.toString(), result);
    }

    @Test
    public void test_some_simple_examples() {
        String[] lines = {
                "<h1>Nayeem loves counseling</h1>",
                "<h1><h1>Sanjay has no watch</h1></h1><par>So wait for a while<par>",
                "<Amee>safat codes like a ninja</amee>",
                "<SA premium>Imtiaz has a secret crash</SA premium>"
        };
        StringBuilder answers = new StringBuilder()
                .append("Nayeem loves counseling")
                .append(System.lineSeparator())
                .append("Sanjay has no watch")
                .append(System.lineSeparator())
                .append("None")
                .append(System.lineSeparator())
                .append("Imtiaz has a secret crash")
                .append(System.lineSeparator());
        String result = t.extract(lines);
        assertEquals(answers.toString(), result);
    }

    @Test
    public void test_case_1() {
        String[] lines = {
                "<h1>some</h1>",
                "<h1>had<h1>public</h1></h1>",
                "<h1>had<h1>public</h1515></h1>",
                "<h1><h1></h1></h1>",
                "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<",
                ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>",
                "<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>",
                "<>hello</>",
                "<>hello</><h>dim</h>",
                "<>hello</><h>dim</h>>>>>"
        };
        StringBuilder answers = new StringBuilder()
                .append("some")
                .append(System.lineSeparator())
                .append("public")
                .append(System.lineSeparator())
                .append("None")
                .append(System.lineSeparator())
                .append("None")
                .append(System.lineSeparator())
                .append("None")
                .append(System.lineSeparator())
                .append("None")
                .append(System.lineSeparator())
                .append("None")
                .append(System.lineSeparator())
                .append("None")
                .append(System.lineSeparator())
                .append("dim")
                .append(System.lineSeparator())
                .append("dim")
                .append(System.lineSeparator());
        String result = t.extract(lines);
        assertEquals(answers.toString(), result);
    }
}
