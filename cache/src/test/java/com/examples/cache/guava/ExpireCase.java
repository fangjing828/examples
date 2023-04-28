package com.examples.cache.guava;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Guava 的过期策略
 */
public class ExpireCase {
    @Test
    void testExpireAfterWrite() throws InterruptedException {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(500, TimeUnit.MILLISECONDS)
                .build();
        cache.put("key-1", "value-1");
        TimeUnit.MILLISECONDS.sleep(600);
        assertNull(cache.getIfPresent("key-1"));
    }

    @Test
    void testExpireAfterAccess() throws InterruptedException {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .expireAfterAccess(500, TimeUnit.MILLISECONDS)
                .build();
        cache.put("key-1", "value-1");
        TimeUnit.MILLISECONDS.sleep(600);
        assertNull(cache.getIfPresent("key-1"));
    }

    /**
     * 取最小值
     * @throws InterruptedException
     */
    @Test
    void testExpireAfterWriteAndAccess() throws InterruptedException {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(1000, TimeUnit.MILLISECONDS)
                .expireAfterAccess(500, TimeUnit.MILLISECONDS)
                .build();
        cache.put("key-1", "value-1");
        TimeUnit.MILLISECONDS.sleep(600);
        assertNull(cache.getIfPresent("key-1"));
    }
}
