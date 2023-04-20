package com.examples.concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

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
        Thread.setDefaultUncaughtExceptionHandler(null);
    }
}
