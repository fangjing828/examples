package com.examples.protobuf.performance;

import lombok.SneakyThrows;

public class Person implements Cloneable {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Person copy() {
        return new Person(name, age);
    }

    @SneakyThrows
    public Person clone() {
        return (Person) super.clone();
    }
}
