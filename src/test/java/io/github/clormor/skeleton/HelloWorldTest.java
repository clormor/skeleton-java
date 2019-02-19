package io.github.clormor.skeleton;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HelloWorldTest {

    private HelloWorld hello;

    @Before
    public void setup() {
        hello = new HelloWorld();
    }

    @Test
    public void say_hello() {
        assertEquals("hello", hello.sayHello());
    }
}
