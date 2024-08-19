package com.examples.cache.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

public class CaffeineWeight {
    public static void main(String[] args) throws InterruptedException {
        Cache<Integer, Integer> cache = Caffeine.newBuilder()
                .initialCapacity(2)
                .maximumSize(5)
                .evictionListener((key, value, cause) -> {
                    try {
                        System.out.println(cause + Thread.currentThread().getName());
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                })
                .build();
        for (int i = 0; i < 10; i++) {
            cache.put(i, i);
        }
        TimeUnit.HOURS.sleep(1);
    }
}
