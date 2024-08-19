package com.examples.concurrent.metric;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class CounterBucket<K> {
    private volatile long time;
    private final Map<K, AtomicLong> counter;

    public CounterBucket(long time) {
        this.time = time;
        this.counter = new ConcurrentHashMap<>();
    }

    public long incrementAndCount(K key) {
        return counter.computeIfAbsent(key, k -> new AtomicLong()).incrementAndGet();
    }

    public Map<K, AtomicLong> count() {
        return counter;
    }

    public long time() {
        return time;
    }

    public void reset(long time) {
        this.counter.clear();
        this.time = time;
    }
}
