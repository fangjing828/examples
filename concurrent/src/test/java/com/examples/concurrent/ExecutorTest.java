package com.examples.concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

class ExecutorTest {
    ExecutorService executor = Executors.newCachedThreadPool();
    int n = 5;

    /**
     * 测试线程池并发执行任务
     */
    @Test
    void testExecute() {
        CountDownLatch countDownLatch = new CountDownLatch(n);
        for (int i = 0; i < n; i++) {
            executor.execute(() -> {
                sleep(100);
                countDownLatch.countDown();
            });
        }
        assertTrue(await(countDownLatch, 3000, TimeUnit.MILLISECONDS));
    }


    /**
     * 测试线程池并发提交任务
     */
    @Test
    void testSubmit() {
        CountDownLatch countDownLatch = new CountDownLatch(n);
        for (int i = 0; i < n; i++) {
            executor.submit(() -> {
                sleep(100);
                countDownLatch.countDown();
            });
        }
        assertTrue(await(countDownLatch, 3000, TimeUnit.MILLISECONDS));
    }

    @Test
    void testSubmit_processFuture() throws ExecutionException, InterruptedException {
        Future<Long> futureTask = executor.submit(() -> {
            TimeUnit.MILLISECONDS.sleep(10);
            return 1L;
        });
        while (!futureTask.isDone()) {
            System.out.println("FutureTask is not finished yet...");
            TimeUnit.MILLISECONDS.sleep(1);

        }
        assertEquals(1, futureTask.get());
    }

    /**
     * 测试线程池取消任务执行
     */
    @Test
    void testCancel() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Future future = executor.submit(() -> {
            System.out.println("xxx");
            sleep(100);
            countDownLatch.countDown();
        });
        future.cancel(true);
        assertFalse(await(countDownLatch, 1000, TimeUnit.MILLISECONDS));
    }

    boolean await(CountDownLatch countDownLatch, int timeout, TimeUnit timeUnit) {
        try {
            return countDownLatch.await(timeout, timeUnit);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    void sleep(long sleepTimeInMills) {
        try {
            TimeUnit.MILLISECONDS.sleep(sleepTimeInMills);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
