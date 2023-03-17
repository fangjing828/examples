package com.examples.io.example;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @author alex.fang
 * @date 2023/2/15
 */
public class AsyncAndBlockIOExample {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        // 异步阻塞从流中读取数据
        ByteArrayInputStream is = new ByteArrayInputStream("Hello World!".getBytes());
        CountDownLatch countDownLatch = new CountDownLatch(2);
        read(is, b -> {
            countDownLatch.countDown();
            System.out.println("read:" + new String(b));
            // 同步非阻塞将数据写入流
            try {
                write(b, writeResult -> {
                    countDownLatch.countDown();
                    System.out.println("write:" + writeResult);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        countDownLatch.await(1, TimeUnit.SECONDS);
    }

    public static Future<byte[]> read(InputStream is, Consumer<byte[]> consumer) throws IOException {
        CompletableFuture<byte[]> future = new CompletableFuture<>();
        future.thenAccept(consumer);
        CompletableFuture.runAsync(() -> {
            byte[] b = new byte[12];
            try {
                is.read(b);
                future.complete(b);
            } catch (Throwable e) {
                future.completeExceptionally(e);
            }
        });
        return future;
    }

    public static Future<OutputStream> write(byte[] b, Consumer<OutputStream> consumer) throws IOException {
        CompletableFuture<OutputStream> future = new CompletableFuture<>();
        future.thenAccept(consumer);
        CompletableFuture.runAsync(() -> {
            try {
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                os.write(b);
                future.complete(os);
            } catch (Throwable e) {
                future.completeExceptionally(e);
            }
        });
        return future;
    }
}
