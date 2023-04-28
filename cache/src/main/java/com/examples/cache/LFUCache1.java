package com.examples.cache;

import java.util.HashMap;
import java.util.Map;

public class LFUCache1 implements Cache {
    private final Map<String, Node> map = new HashMap<>();
    private final int capacity;
    private final Node head;
    private final Node tail;

    public LFUCache1(int capacity) {
        this.capacity = capacity;
        this.head = new Node();
        this.tail = new Node();
        this.head.next = tail;
        this.tail.previous = head;
    }

    @Override
    public void put(String key, String value) {
        Node node = map.get(key);
        if(node != null){
            node.value = value;
            refreshNode(node);
        }else{
            //如果元素满了
            if(map.size() == capacity){
                //直接移除最前面的元素，因为这个节点就是频次最小，且最久未访问的节点
                map.remove(head.next.key);
                removeNode(head.next);
            }
            Node newNode = new Node(key, value);
            //把新元素添加进来
            addNode(newNode);
            map.put(key,newNode);
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

    void print() {
        Node node = head.next;
        while (node != tail) {
            System.out.println(node.key);
            node = node.next;
        }
        System.out.println();
    }

    void refreshNode(Node node) {
        node.frequency = node.frequency++;
        Node nextNode = node.next;
        //先把当前元素删除
        removeNode(node);
        //遍历到符合要求的节点
        while (node.frequency >= nextNode.frequency && nextNode != tail){
            nextNode = nextNode.next;
        }
        //把当前元素插入到nextNode前面
        node.next = nextNode;
        node.previous = nextNode.previous;
        nextNode.previous.next = node;
        nextNode.previous = node;
    }

    //移除元素
    private void removeNode(Node node){
        node.previous.next = node.next;
        node.next.previous = node.previous;
    }

    private void addNode(Node node){
        node.previous = head;
        node.next = head.next;
        head.next.previous = node;
        head.next = node;
        refreshNode(node);
    }


    @Override
    public int size() {
        return map.size();
    }

    static class Node {
        private String key;
        private String value;

        private int frequency;

        private Node previous;
        private Node next;

        public Node() {
        }

        public Node(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
