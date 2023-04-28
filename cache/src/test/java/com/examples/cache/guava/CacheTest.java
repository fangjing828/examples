package com.examples.cache.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CacheTest {
    @Test
    void testInvalidate_whenKeyNotExist_thenDoNothing() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .removalListener(notification -> {
                    System.out.println(notification.getKey());
                    System.out.println(notification.getValue());
                    countDownLatch.countDown();
                })
                .build();
        cache.invalidate("key-1");
        assertFalse(countDownLatch.await(1, TimeUnit.SECONDS));
    }

    @Test
    void testInvalidate_whenKeyExist_thenRemoveKey() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .removalListener(notification -> {
                    System.out.println(notification.getKey());
                    System.out.println(notification.getValue());
                    countDownLatch.countDown();
                })
                .build();
        cache.put("key-1", "value-1");
        cache.invalidate("key-1");
        assertTrue(countDownLatch.await(1, TimeUnit.SECONDS));
    }
}
