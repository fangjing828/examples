package com.examples.start;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SingleInstanceEnumTest {
    @Test
    void testGetName() {
        assertEquals("xyz", SingleInstanceEnum.A_INSTANCE.getName());
        assertEquals("asd", SingleInstanceEnum.B_INSTANCE.getName());
    }
}