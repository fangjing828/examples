package com.examples.start;

public enum SingleInstanceEnum {
    A_INSTANCE("xyz"),
    B_INSTANCE("asd");

    private final String name;

    SingleInstanceEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
