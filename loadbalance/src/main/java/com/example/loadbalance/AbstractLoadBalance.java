package com.example.loadbalance;

import java.util.List;

public abstract class AbstractLoadBalance {
    public <T extends Node> AssignResult<T> assign(List<T> nodes, List<String> topics) {
        AssignResult<T> result = new AssignResult<>();
        for (String topic : topics) {
            result = result.assign(select(nodes), topic);
        }
        return result;
    }

    public abstract <T extends Node> T select(List<T> nodes);
}
