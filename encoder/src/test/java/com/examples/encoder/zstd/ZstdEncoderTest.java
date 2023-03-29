package com.examples.encoder.zstd;

import com.examples.encoder.StreamUtils;
import com.github.luben.zstd.ZstdInputStream;
import com.github.luben.zstd.ZstdOutputStream;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * @author alex.fang
 * @date 2023/3/29
 */
public class ZstdEncoderTest {
    @Test
    void testZstd() throws IOException {
        byte[] content = ZstdEncoderTest.class.getName().getBytes(StandardCharsets.UTF_8);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try (ZstdOutputStream zstdOutputStream = new ZstdOutputStream(os)) {
            zstdOutputStream.write(content);
        }
        byte[] encoded = os.toByteArray();

        try (ZstdInputStream zstdInputStream = new ZstdInputStream(new ByteArrayInputStream(encoded))) {
            byte[] decoded = StreamUtils.read(zstdInputStream);
            assertArrayEquals(content, decoded);
        }
    }
}
