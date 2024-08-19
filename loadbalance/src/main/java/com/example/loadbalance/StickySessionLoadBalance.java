package com.example.loadbalance;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class StickySessionLoadBalance {
    public <T extends Node> T select(List<T> nodes) {
        Session session = Session.current();
        for (T node : nodes) {
            if (node.containsSession(session)) {
                return node;
            }
        }

        T node = nodes.get(ThreadLocalRandom.current().nextInt(nodes.size()));
        node.bindSession(session);
        return node;
    }
}
