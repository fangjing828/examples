package com.example.loadbalance;

public interface Node {
    String key();

    default int weight() {
        return 0;
    }

    default void bindSession(Session session) {

    }
    default boolean containsSession(Session session) {
        return false;
    }
}
