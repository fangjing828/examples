package com.examples.fastjson;

/**
 * @author alex.fang
 * @date 2023/2/14
 */
public class CircularReferenceA {
    private String name = CircularReferenceA.class.getSimpleName();
    private CircularReferenceB data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CircularReferenceB getData() {
        return data;
    }

    public void setData(CircularReferenceB data) {
        this.data = data;
    }
}
