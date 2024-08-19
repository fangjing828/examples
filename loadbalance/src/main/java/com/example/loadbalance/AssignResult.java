package com.example.loadbalance;


import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import org.checkerframework.checker.units.qual.A;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AssignResult<T extends Node> {
    private final Map<String, T> topicNode;

    public AssignResult() {
        this(Collections.emptyMap());
    }

    public AssignResult(Map<String, T> topicNode) {
        this.topicNode = Collections.unmodifiableMap(topicNode);
    }

    public AssignResult<T> assign(T node, String topic) {
        Map<String, T> topicNode = new HashMap<>(this.topicNode);
        topicNode.put(topic, node);
        return new AssignResult<>(topicNode);
    }

    public AssignResult<T> minDiff(AssignResult<T> target) {
        Map<String, T> sourceTopicNode = new HashMap<>();
        SetMultimap<T, String> sourceNodeTopics = HashMultimap.create();
        this.topicNode.forEach((topic, node) -> {
            sourceTopicNode.put(topic, node);
            sourceNodeTopics.put(node, topic);
        });

//        Map<String, T> targetTopicNode = new HashMap<>();
        SetMultimap<T, String> targetNodeTopics = HashMultimap.create();
        target.topicNode.forEach((topic, node) -> {
//            targetTopicNode.put(topic, node);
            targetNodeTopics.put(node, topic);
        });

        Map<String, T> result = new HashMap<>();
        for (String topic : this.topicNode.keySet()) {
            T node = sourceTopicNode.get(topic);

            Set<String> switchTopics = targetNodeTopics.get(node);
            if (switchTopics.isEmpty()) {
                result.put(topic, node);
                sourceTopicNode.remove(topic, node);
                continue;
            }

            String switchTopic = switchTopics.contains(topic) ? topic : switchTopics.iterator().next();
            Node switchNode = sourceTopicNode.get(switchTopic);
            if (switchNode == null) {

            }
//            if (!sourceTopicNode.containsKey(targetTopic)) {
//                result.put(sourceTopic, sourceNode);
//                sourceTopicNode.remove(sourceTopic, sourceNode);
//                continue;
//            }
//
//            if (sourceTopic.equals(targetTopic)) {
//                result.put(sourceTopic, sourceNode);
//                sourceTopicNode.remove(sourceTopic, sourceNode);
//                targetNodeTopics.remove(sourceNode, targetTopic);
//                continue;
//            }
//            if (sourceNode.equals(targetNode)) {
//                result.put(sourceTopic, sourceNode);
//            }
        }
        return new AssignResult<>(result);
    }


    public T getNode(String topic) {
        return topicNode.get(topic);
    }
}
