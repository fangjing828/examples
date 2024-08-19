package com.examples.concurrent.metric;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;


class CounterBufferTest {
    CounterBuffer counterBuffer;

    @BeforeEach
    void setUp() {
        counterBuffer = new CounterBuffer(10, 2000);
    }

    @Test
    void testLatestBucketTime(){
        System.out.println(counterBuffer.latestBucketTime());
        counterBuffer.bucket().incrementAndCount(1L);
        counterBuffer.bucket().incrementAndCount(1L);
        Map<Long, AtomicLong> result =  counterBuffer.count();
    }
}