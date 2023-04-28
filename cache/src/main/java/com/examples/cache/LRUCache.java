package com.examples.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache implements Cache {
    private final LinkedHashMap<String, String> map;

    public LRUCache(int capacity) {
        this.map = new LinkedHashMap<>(16, 0.75f, true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                return size() > capacity;
            }
        };
    }

    @Override
    public void put(String key, String value) {
        map.put(key, value);
    }

    @Override
    public String get(String key) {
        return map.get(key);
    }

    @Override
    public int size() {
        return map.size();
    }
}
