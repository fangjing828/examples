package com.example.loadbalance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RoundRobinLoadBalanceTest {
    RoundRobinLoadBalance lb;

    @BeforeEach
    void setUp() {
        lb = new RoundRobinLoadBalance();
    }

    @Test
    void testSelect_WithIntegerMultiple() {
        int N = 10;
        int M = 100;
        List<IntNode> nodes = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            nodes.add(IntNode.of(i));
        }

        Map<Integer, AtomicInteger> counter = new HashMap<>();
        for (int i = 0; i < M; i++) {
            counter.computeIfAbsent(lb.select(nodes).getValue(), k -> new AtomicInteger()).incrementAndGet();
        }

        assertEquals(M, counter.values().stream().mapToInt(AtomicInteger::get).sum());
        for (int i = 0; i < N; i++) {
            assertEquals(counter.get(i).get(), M / N);
        }
    }

    @Test
    void testSelect() {
        int N = 10;
        int M = 106;
        List<IntNode> nodes = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            nodes.add(IntNode.of(i));
        }

        Map<Integer, AtomicInteger> counter = new HashMap<>();
        for (int i = 0; i < M; i++) {
            counter.computeIfAbsent(lb.select(nodes).getValue(), k -> new AtomicInteger()).incrementAndGet();
        }

        assertEquals(M, counter.values().stream().mapToInt(AtomicInteger::get).sum());
        for (int i = 0; i < N; i++) {
            int diff = counter.get(i).get() - M / N;
            assertTrue(diff == 0 || diff == 1);
        }
    }
}