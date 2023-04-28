package com.examples.concurrent.parallel;

import java.util.stream.IntStream;

public class ParallelTest {
    static {
        final int SUM = IntStream.range(0, 100)
                .parallel()
                .reduce((n, m) -> n + m)
                .getAsInt();
        System.out.println(SUM);
    }

    public static void main(String[] args) {
        System.out.println("Finished");
    }
}
