package com.examples.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author alex.fang
 * @date 2023/4/3
 */
public class ThreadTest {
    @Test
    void testSetDefaultUncaughtExceptionHandler() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.out.println(String.format("UncaughtException in Thread(%s): %s", t.getName(), e.getMessage()));
            e.printStackTrace();
            countDownLatch.countDown();
        });
        new Thread(() -> Integer.parseInt("abc")).start();
        assertTrue(countDownLatch.await(1, TimeUnit.SECONDS));
    }

    @Test
    void testCancel() throws InterruptedException {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        CountDownLatch countDownLatch = new CountDownLatch(5);
        AtomicLong counter = new AtomicLong();
        ScheduledFuture future = executorService.scheduleWithFixedDelay(() -> {
            System.out.println(counter.incrementAndGet());
            countDownLatch.countDown();
        }, 0, 100, TimeUnit.MILLISECONDS);
        // 取消任务
        future.cancel(false);
        assertFalse(countDownLatch.await(1, TimeUnit.SECONDS));
    }
}
