package com.examples.concurrent.lock;

import java.util.concurrent.CountDownLatch;

public class Adder {
    private int result;

    public void add(int delta) {
        result = result + delta;
    }

    public static void main(String[] args) throws InterruptedException {
        Adder adder = new Adder();
        CountDownLatch countDownLatch = new CountDownLatch(20000);
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    adder.add(1);
                    countDownLatch.countDown();
                }
            }).start();
        }
        countDownLatch.await();
        System.out.println(adder.result);
    }
}
