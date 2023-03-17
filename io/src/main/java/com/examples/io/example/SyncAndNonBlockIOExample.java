package com.examples.io.example;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 同步阻塞 I/O 模型
 *
 * @author alex.fang
 * @date 2023/2/15
 */
public class SyncAndNonBlockIOExample {
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        // 同步非阻塞从流中读取数据
        ByteArrayInputStream is = new ByteArrayInputStream("Hello World!".getBytes());
        Future<byte[]> readResult = read(is);
        while (!Thread.currentThread().isInterrupted()) {
            if (readResult.isDone()) {
                System.out.println("read:" + new String(readResult.get()));
                break;
            }
            TimeUnit.MILLISECONDS.sleep(1);
        }

        // 同步非阻塞将数据写入流
        Future<OutputStream> writeResult = write(readResult.get());
        while (!Thread.currentThread().isInterrupted()) {
            if (writeResult.isDone()) {
                System.out.println("write:" + writeResult.get());
                break;
            }
            TimeUnit.MILLISECONDS.sleep(1);
        }
    }

    public static Future<byte[]> read(InputStream is) throws IOException {
        CompletableFuture<byte[]> future = new CompletableFuture<>();
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

    public static Future<OutputStream> write(byte[] b) throws IOException {
        CompletableFuture<OutputStream> future = new CompletableFuture<>();
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
