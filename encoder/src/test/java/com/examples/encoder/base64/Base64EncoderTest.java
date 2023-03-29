package com.examples.encoder.base64;

import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author alex.fang
 * @date 2023/3/29
 */
 class Base64EncoderTest {
    @Test
    void testBase64() {
        String content = "Hello";
        byte[] encoded = Base64.getEncoder().encode(content.getBytes());
        assertEquals(new String(encoded),  "SGVsbG8=");
        byte[] decoded = Base64.getDecoder().decode(encoded);
        assertEquals(content, new String(decoded));
    }


}
