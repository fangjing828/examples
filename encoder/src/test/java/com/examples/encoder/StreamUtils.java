package com.examples.encoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author alex.fang
 * @date 2023/3/29
 */
public class StreamUtils {
   public static byte[] read(InputStream is) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[512];
        int i;
        while ((i = is.read(buffer)) != -1) {
            os.write(buffer, 0, i);
        }
        return os.toByteArray();
    }
}
