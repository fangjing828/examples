package com.example.loadbalance;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import static com.example.loadbalance.Constants.RECYCLE_PERIOD;

public class RoundRobinLoadBalance extends AbstractLoadBalance {
    private final Map<String, RoundRobin> roundRobinMap = new ConcurrentHashMap<>();

    @Override
    public <T extends Node> T select(List<T> nodes) {
        long now = System.currentTimeMillis();
        long maxCurrent = Long.MIN_VALUE;
        T selectedNode = null;
        RoundRobin selectedRR = null;
        int totalWeight = nodes.size();

        for (T node : nodes) {
            RoundRobin rr = roundRobinMap.computeIfAbsent(node.key(), key -> new RoundRobin());
            long current = rr.increaseCurrent();
            rr.lastUpdate = now;
            if (current > maxCurrent) {
                maxCurrent = current;
                selectedNode = node;
                selectedRR = rr;
            }
        }

        // 清理已经下线的节点
        if (nodes.size() != roundRobinMap.size()) {
            roundRobinMap.entrySet().removeIf(item -> now - item.getValue().lastUpdate > RECYCLE_PERIOD);
        }

        if (selectedNode != null) {
            selectedRR.select(totalWeight);
            return selectedNode;
        }

        return nodes.get(0);
    }

    static class RoundRobin {
        private final AtomicLong current = new AtomicLong();
        private long lastUpdate;

        public long increaseCurrent() {
            return current.incrementAndGet();
        }

        public void select(int total) {
            current.addAndGet(-total);
        }
    }

}
