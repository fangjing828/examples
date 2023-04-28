package com.examples.cache;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class LFUCache implements Cache {
    private final Queue<Node> queue;
    private final Map<String, Node> map;

    private final int capacity;

    //全局自增
    private final AtomicInteger INDEX = new AtomicInteger();


    public LFUCache(int capacity) {
        queue = new PriorityQueue<>(capacity);
        map = new HashMap<>();
        this.capacity = capacity;
    }

    @Override
    public void put(String key, String value) {
        Node node = map.get(key);
        if (node != null) {
            node.value = value;
            refreshNode(node);
        } else {
            if (size() == capacity) {
                map.remove(queue.poll().key);
            }
            Node newNode = new Node(key, value, INDEX.incrementAndGet());
            queue.offer(newNode);
            map.put(key, newNode);
        }
    }

    @Override
    public String get(String key) {
        Node node = map.get(key);
        if (node == null) {
            return null;
        }
        refreshNode(node);
        return node.value;
    }

    void refreshNode(Node node) {
        node.frequency = node.frequency + 1;
        node.index = node.index + 1;
        queue.remove(node);
        queue.offer(node);
    }

    @Override
    public int size() {
        return map.size();
    }

    public void print() {
        List<Node> list = new ArrayList<>(capacity);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.println(node.key);
            list.add(node);
        }
        queue.addAll(list);
        System.out.println();
    }

    static class Node implements Comparable<Node> {
        private final String key;
        private String value;
        private int frequency = 0;
        private int index;

        public Node(String key, String value, int index) {
            this.key = key;
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(Node o) {
            int res = Integer.compare(frequency, o.frequency);
            if (res == 0) {
                res = Integer.compare(index, o.index);
            }
            return res;
        }
    }
}
