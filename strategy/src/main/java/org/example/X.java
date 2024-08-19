package org.example;

import java.util.stream.IntStream;

public class X {
    public static void main(String[] args) {
        for (int i : IntStream.range(0, 10).toArray()) {
            System.out.println(i);
        }
    }
}
