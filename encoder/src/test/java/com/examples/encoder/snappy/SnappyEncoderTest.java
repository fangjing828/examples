package com.examples.encoder.snappy;

import org.junit.jupiter.api.Test;
import org.xerial.snappy.Snappy;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * @author alex.fang
 * @date 2023/3/29
 */
public class SnappyEncoderTest {
    @Test
    void testSnappy() throws IOException {
        byte[] content = SnappyEncoderTest.class.getName().getBytes(StandardCharsets.UTF_8);

        byte[] encoded = Snappy.compress(content);
        byte[] decoded = Snappy.uncompress(encoded);
        assertArrayEquals(content, decoded);
    }
}
