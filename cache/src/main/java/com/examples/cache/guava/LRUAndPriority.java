package com.examples.cache.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class LRUAndPriority {
    public static void main(String[] args) {
        Cache<Integer,Integer> x = CacheBuilder
                .newBuilder()
                .maximumSize(10)
                .removalListener(notification -> {
                    System.out.println(notification.getCause());
//                    System.out.println(notification);
                })
                .build();

        for (int i = 0; i < 100; i++) {
            x.put(0, i);
        }
    }
}
