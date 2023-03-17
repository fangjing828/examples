package com.examples.fastjson;

import com.alibaba.fastjson.JSON;

/**
 * @author alex.fang
 * @date 2023/2/14
 */
public class CircularCall {
    private String name = CircularCall.class.getSimpleName();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJsonString() {
        return JSON.toJSONString(this);
    }
}
