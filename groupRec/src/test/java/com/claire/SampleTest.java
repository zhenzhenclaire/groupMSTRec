package com.claire;

import org.junit.Test;

import com.claire.Sample;

import static org.junit.Assert.*;

public class SampleTest {
    @Test
    public void canConstructAPersonWithAName() {
        Sample person = new Sample("Larry");
        assertEquals("Larry", person.getName());
    }
}
