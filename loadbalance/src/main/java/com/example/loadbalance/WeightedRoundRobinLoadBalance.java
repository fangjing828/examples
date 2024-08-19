package com.example.loadbalance;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import static com.example.loadbalance.Constants.RECYCLE_PERIOD;

public class WeightedRoundRobinLoadBalance {
    private final Map<String, WeightedRoundRobin> weightedRoundRobinMap = new ConcurrentHashMap<>();

    public <T extends Node> T select(List<T> nodes) {
        long now = System.currentTimeMillis();
        long maxCurrent = Long.MIN_VALUE;
        T selectedNode = null;
        WeightedRoundRobin selectedWRR = null;
        int totalWeight = 0;

        for (T node : nodes) {
            int weight = node.weight();
            if (weight == 0) {
                continue;
            }
            totalWeight+=weight;
            WeightedRoundRobin wrr = weightedRoundRobinMap.computeIfAbsent(node.key(), k -> {
                WeightedRoundRobin result = new WeightedRoundRobin();
                result.setWeight(weight);
                return result;
            });

            if (wrr.weight != weight) {
                wrr.weight = weight;
            }

            long current = wrr.increaseCurrent();
            wrr.lastUpdate = now;
            if (current > maxCurrent) {
                maxCurrent = current;
                selectedNode = node;
                selectedWRR = wrr;
            }
        }

        if (nodes.size() != weightedRoundRobinMap.size()) {
            weightedRoundRobinMap.entrySet().removeIf(item -> now - item.getValue().lastUpdate > RECYCLE_PERIOD);
        }

        if (selectedNode != null) {
            selectedWRR.select(totalWeight);
            return selectedNode;
        }

        return nodes.get(0);

    }
    static class WeightedRoundRobin {
        private final AtomicLong current = new AtomicLong(0);
        private long lastUpdate;
        private int weight;

        public long increaseCurrent() {
            return this.current.addAndGet(weight);
        }

        public void setWeight(int weight) {
            this.weight = weight;
            this.current.set(0);
        }

        public void select(int total) {
            this.current.addAndGet(-total);
        }
    }
}
