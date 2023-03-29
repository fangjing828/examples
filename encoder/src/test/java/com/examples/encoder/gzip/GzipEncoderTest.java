package com.examples.encoder.gzip;

import com.examples.encoder.StreamUtils;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.Inflater;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author alex.fang
 * @date 2023/3/29
 */
public class GzipEncoderTest {
    @Test
    void testGzip() throws IOException {
        byte[] content = GzipEncoderTest.class.getName().getBytes(StandardCharsets.UTF_8);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(os)) {
            gzipOutputStream.write(content);
        }
        byte[] encoded = os.toByteArray();

        try (GZIPInputStream gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(encoded))) {
            byte[] decoded = StreamUtils.read(gzipInputStream);
            assertArrayEquals(content, decoded);
        }
    }

    @Test
    void testFlat() throws DataFormatException {
        Deflater deflater = new Deflater();
        Inflater inflater = new Inflater();

        String text = "Hello";
        deflater.setInput(text.getBytes(StandardCharsets.UTF_8));
        deflater.finish();
        byte decode[] = new byte[1024];
        int decodeSize = deflater.deflate(decode);

        inflater.setInput(decode, 0, decodeSize);
        inflater.finished();
        byte encode[] = new byte[1024];
        int encodeSize = inflater.inflate(encode);

        assertArrayEquals(text.getBytes(StandardCharsets.UTF_8),
                Arrays.copyOf(encode, text.length()));

        // compressed String
        System.out.println("Compressed String :"
                + new String(decode)
                + "\n Size " + decodeSize);

        // original String
        System.out.println("Original String :"
                + text + "\n Size "
                + text.length());

        // end
        deflater.end();
    }
}
