package com.examples.cache.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.Weigher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class EvictCase {
    /**
     * 固定 cache 容量，在数据访问频率和次数一致的情况下，淘汰最早入队的数据（FIFO）。
     */
    @Test
    void testMaximumSize() {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(2)
                .build();
        cache.put("key-1", "value-1");
        cache.put("key-2", "value-2");
        cache.put("key-3", "value-3");
        assertNull(cache.getIfPresent("key-1"));
    }

    /**
     * 固定 cache 容量，在数据访问频率和次数不一致的情况下，淘汰最久未访问的数据（LRU）。
     */
    @Test
    void testMaximumSize_x() {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(2)
                .build();
        cache.put("key-1", "value-1");
        cache.put("key-2", "value-2");
        for (int i = 0; i < 10; i++) {
            cache.getIfPresent("key-2");
        }
        cache.getIfPresent("key-1");
        cache.put("key-3", "value-3");
        assertNull(cache.getIfPresent("key-2"));
    }

    /**
     * 权重大于等于 0
     * 低于指定权重
     */
    @Test
    void testMaximumSize_withWeight() {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumWeight(4)
                .weigher((key, value) -> Integer.parseInt(((String)key).split("-")[1]))
                .build();
        cache.put("key-0", "value-0");
        cache.put("key-1", "value-1");
        cache.put("key-2", "value-2");
        cache.put("key-3", "value-3");
        cache.put("key-4", "value-4");

        assertNull(cache.getIfPresent("key-4"));
    }
}
