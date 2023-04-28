package com.examples.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LFUCacheTest {
    LFUCache cache;
    int capacity = 8;

    @BeforeEach
    void setUp() {
        cache = new LFUCache(capacity);
    }

    @Test
    void testPut() {
        // cache size less or equals to capacity
        for (int i = 1; i <= capacity; i++) {
            cache.put("key-" + i, "value-" + i);
            assertEquals(i, cache.size());
        }
        for (int i = 1, expired = 1; i < capacity; i++, expired++) {
            int offset = i + capacity;
            cache.put("key-" + offset, "value-" + offset);
            assertEquals(capacity, cache.size());
            // 淘汰最久未使用的元素
            assertNull(cache.get("key-" + expired));
        }
    }

    @Test
    void testPut_AfterGet() {
        // cache size less or equals to capacity
        for (int i = 1; i <= capacity; i++) {
            cache.put("key-" + i, "value-" + i);
            assertEquals(i, cache.size());
        }
        cache.print();

        for (int i = 1, expired = 1; i <= capacity / 2; i++, expired++) {
            int offset = i + capacity;
            cache.get("key-" + expired); // 读不改变对应键淘汰参数
            expired++;
            cache.print();

            cache.put("key-" + offset, "value-" + offset);
            cache.print();

            assertEquals(capacity, cache.size());
            // 淘汰最久未使用的元素
            assertNull(cache.get("key-" + expired));
        }
    }
}