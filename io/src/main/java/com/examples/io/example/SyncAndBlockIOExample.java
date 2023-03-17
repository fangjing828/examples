package com.examples.io.example;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 同步阻塞 I/O 模型
 *
 * @author alex.fang
 * @date 2023/2/15
 */
public class SyncAndBlockIOExample {

    public static void main(String[] args) throws IOException {
        // 同步阻塞从流中读取数据
        ByteArrayInputStream is = new ByteArrayInputStream("Hello World!".getBytes());
        byte[] b = read(is);
        // 同步阻塞将数据写入流
        OutputStream os = write(b);
    }
    public static byte[] read(InputStream is) throws IOException {
        byte[] b = new byte[12];
        is.read(b);
        System.out.println("read:" + new String(b));
        return b;
    }

    public static OutputStream write(byte[] b) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        os.write(b);
        System.out.println("write:" + os);
        return os;
    }
}
