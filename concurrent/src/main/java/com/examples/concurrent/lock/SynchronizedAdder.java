package com.examples.concurrent.lock;

import java.util.concurrent.CountDownLatch;

public class SynchronizedAdder {
    private int result;

    public void add(int delta) {
        synchronized (this) {
            result = result + delta;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedAdder adder = new SynchronizedAdder();
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
