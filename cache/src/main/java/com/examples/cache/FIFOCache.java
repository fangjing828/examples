package com.examples.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class FIFOCache implements Cache {
    private final LinkedHashMap<String, String> map;

    public FIFOCache(int capacity) {
        this.map = new LinkedHashMap<>(){
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
