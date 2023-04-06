package com.examples.thread;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

public class ConcurrentTaskTest {
    ExecutorService executor = Executors.newCachedThreadPool();
    int n = 5;

    @Test
    public void executeConcurrentTask() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(n);
        for (int i = 0; i < n; i++) {
            executor.submit(() -> {
                sleep(ThreadLocalRandom.current().nextInt(1000));
                countDownLatch.countDown();
            });
        }
        assertTrue(countDownLatch.await(3000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void executeConcurrentTask_thenTimeout() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(n);
        for (int i = 0; i < n; i++) {
            long executeTime = n - i == 1 ? Integer.MAX_VALUE : ThreadLocalRandom.current().nextInt(1000);
            executor.submit(() -> {
                sleep(executeTime);
                countDownLatch.countDown();
            });
        }
        assertFalse(countDownLatch.await(3000, TimeUnit.MILLISECONDS));
    }

    @Test
    public void executeConcurrentTask_ThreadPool() throws InterruptedException, ExecutionException {
        Future<Long> futureTask = executor.submit(() -> sleep(10));
        while (!futureTask.isDone()) {
            System.out.println("FutureTask is not finished yet...");
        }
        assertEquals(1, futureTask.get());
    }

    @Test
    public void executeConcurrentTask_CompletableFuture() throws InterruptedException, ExecutionException {
        CompletableFuture<Long> completableFuture = CompletableFuture.supplyAsync(() -> sleep(10));
        while (!completableFuture.isDone()) {
            System.out.println("CompletableFuture is not finished yet...");
        }
        assertEquals(1, completableFuture.get());
    }

    @Test
    @Timeout(value = 2)
    public void testCompletableFuture_allExecuteSuccess() throws InterruptedException, ExecutionException {
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
    public void testCompletableFuture_FirstExecuteFailed() throws InterruptedException, ExecutionException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        CompletableFuture<Long>[] futures = new CompletableFuture[]{CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException();
        }),
                CompletableFuture.supplyAsync(() -> {
                    countDownLatch.countDown();
                    return System.currentTimeMillis();
                }),
                CompletableFuture.supplyAsync(() -> {
                    countDownLatch.countDown();
                    return System.currentTimeMillis();
                })};
        assertThrows(ExecutionException.class, () -> CompletableFuture.allOf(futures).get());
        assertEquals(1, countDownLatch.getCount());
    }

    @Test
    @Timeout(value = 2)
    public void testCompletableFuture_FirstExecuteTimeout() {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        CompletableFuture[] futures = new CompletableFuture[]{CompletableFuture.supplyAsync(() -> {
            sleep(5000);
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


    long sleep(long sleepTimeInMills) {
        try {
            TimeUnit.MILLISECONDS.sleep(sleepTimeInMills);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return 1;
    }
}
