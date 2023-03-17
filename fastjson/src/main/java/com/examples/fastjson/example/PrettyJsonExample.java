package com.examples.fastjson.example;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * @author alex.fang
 * @date 2023/2/14
 */
public class PrettyJsonExample {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");
        map.put("key_1", "value_1");
        map.put("key_2", "value_2");
        System.out.println(JSON.toJSONString(map, true));
    }
}
