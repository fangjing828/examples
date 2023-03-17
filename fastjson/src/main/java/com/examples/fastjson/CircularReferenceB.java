package com.examples.fastjson;

/**
 * @author alex.fang
 * @date 2023/2/14
 */
public class CircularReferenceB {
    private String name = CircularReferenceB.class.getSimpleName();
    private CircularReferenceA data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CircularReferenceA getData() {
        return data;
    }

    public void setData(CircularReferenceA data) {
        this.data = data;
    }
}
