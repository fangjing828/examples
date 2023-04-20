package com.examples.start;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SampleEnumTest {
    @Test
    void testValueOf() {
        for (SampleEnum sampleEnum : SampleEnum.values()) {
            assertSame(sampleEnum, SampleEnum.valueOf(sampleEnum.name()));
        }
        assertThrows(IllegalArgumentException.class, () -> SampleEnum.valueOf("xyz"));
    }


}