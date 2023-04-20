package com.examples.concurrent;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CompletableFutureTest {
    @Test
    public void testSupplyAsync() throws InterruptedException, ExecutionException {
        CompletableFuture<Long> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return 1L;
        });
        while (!completableFuture.isDone()) {
            System.out.println("CompletableFuture is not finished yet...");
            TimeUnit.MILLISECONDS.sleep(1);
        }
        assertEquals(1, completableFuture.get());
    }

    @Test
    @Timeout(value = 2)
    public void testAllOf() throws InterruptedException, ExecutionException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        CompletableFuture<Long>[] futures = new CompletableFuture[]{CompletableFuture.supplyAsync(() -> {
            countDownLatch.countDown();
            return System.currentTimeMillis();
        }),
                CompletableFuture.supplyAsync(() -> {
                    countDownLatch.countDown();
                    return System.currentTimeMillis();
                }),
                CompletableFuture.supplyAsync(() -> {
                    countDownLatch.countDown();
                    return System.currentTimeMillis();
                })};
        CompletableFuture.allOf(futures).get();
        assertEquals(0, countDownLatch.getCount());
    }

    @Test
    @Timeout(value = 2)
    public void testAllOf_whenFirstTaskExecuteFailed() throws InterruptedException, ExecutionException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        CompletableFuture<Long>[] futures = new CompletableFuture[]{CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException();
        }),
                CompletableFuture.supplyAsync(() -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    countDownLatch.countDown();
                    return System.currentTimeMillis();
                }),
                CompletableFuture.supplyAsync(() -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    countDownLatch.countDown();
                    return System.currentTimeMillis();
                })};
        assertThrows(ExecutionException.class, () -> CompletableFuture.allOf(futures).get());
        assertEquals(1, countDownLatch.getCount());
    }

    @Test
    @Timeout(value = 2)
    public void testAllOf_whenFirstTaskExecuteTimeout() {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        CompletableFuture[] futures = new CompletableFuture[]{CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return System.currentTimeMillis();
        }),
                CompletableFuture.supplyAsync(() -> {
                    countDownLatch.countDown();
                    return System.currentTimeMillis();
                }),
                CompletableFuture.supplyAsync(() -> {
                    countDownLatch.countDown();
                    return System.currentTimeMillis();
                })};
        assertThrows(TimeoutException.class, () -> CompletableFuture.allOf(futures).get(1000, TimeUnit.MILLISECONDS));
        assertEquals(1, countDownLatch.getCount());
    }
}
