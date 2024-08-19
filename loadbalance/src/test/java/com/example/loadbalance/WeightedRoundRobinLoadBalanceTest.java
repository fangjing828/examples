package com.example.loadbalance;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WeightedRoundRobinLoadBalanceTest {
    WeightedRoundRobinLoadBalance lb;

    @BeforeEach
    void setUp() {
        lb = new WeightedRoundRobinLoadBalance();
    }

    @Test
    void testSelect_WithIntegerMultipleTotalWeight() {
        int N = 10;
        int M = 110;
        List<IntNode> nodes = new ArrayList<>();
        int totalWeight = 0;
        for (int i = 1; i <= N; i++) {
            nodes.add(IntNode.of(i, i));
            totalWeight += i;
        }

        Map<Integer, AtomicInteger> counter = new HashMap<>();
        for (int i = 0; i < M; i++) {
            counter.computeIfAbsent(lb.select(nodes).getValue(), k -> new AtomicInteger()).incrementAndGet();
        }

        assertEquals(M, counter.values().stream().mapToInt(AtomicInteger::get).sum());
        for (int i = 1; i <= N; i++) {
            System.out.println(i);
            assertEquals(counter.get(i).get(), (M / totalWeight) * i);
        }
    }


    @Test
    void testSelect() {
        int N = 10;
        for (int m = 0; m <= 110; m++) {
            lb = new WeightedRoundRobinLoadBalance();
            List<IntNode> nodes = new ArrayList<>();
            int totalWeight = 0;
            for (int i = 1; i <= N; i++) {
                nodes.add(IntNode.of(i, i));
                totalWeight += i;
            }

            Map<Integer, AtomicInteger> counter = new HashMap<>();
            for (int i = 0; i < m; i++) {
                counter.computeIfAbsent(lb.select(nodes).getValue(), k -> new AtomicInteger()).incrementAndGet();
            }

            assertEquals(m, counter.values().stream().mapToInt(AtomicInteger::get).sum());
            for (int i = 1; i <= N; i++) {
                int weight = i;
                int diff = counter.getOrDefault(i, new AtomicInteger()).get() - (m / totalWeight) * weight;
                assertTrue(diff >= 0 && diff <= weight);
            }
        }
    }
}