package com.example.loadbalance;

public class IntNode implements Node {
    private final String key;
    private final int value;

    private int weight;

    public IntNode(int value) {
        this.value = value;
        this.key = Integer.toString(value);
    }

    public IntNode(int value, int weight) {
        this(value);
        this.weight = weight;
    }

    @Override
    public String key() {
        return key;
    }


    @Override
    public int weight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "IntNode{" +
                "key='" + key + '\'' +
                ", value=" + value +
                ", weight=" + weight +
                '}';
    }

    static IntNode of(int value) {
        return new IntNode(value);
    }

    static IntNode of(int value, int weight) {
        return new IntNode(value, weight);
    }
}
