package com.examples.encoder.guava;

import com.google.common.primitives.Longs;
import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author alex.fang
 * @date 2023/3/30
 */
class GuavaEncoderTest {
    @Test
    void testGuava() {
        long lNumber = 1234;
        // encode
        byte[] encoded = Base64.getEncoder().encode(Longs.toByteArray(lNumber));
        assertEquals(new String(encoded),  "AAAAAAAABNI=");
        byte[] decoded = Base64.getDecoder().decode(encoded);
        assertEquals(lNumber, Longs.fromByteArray(decoded));
    }
}
