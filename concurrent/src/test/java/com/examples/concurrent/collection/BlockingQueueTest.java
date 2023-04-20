package com.examples.concurrent.collection;

import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicLong;

public class BlockingQueueTest {
    public static void main(String[] args) {
        LinkedBlockingDeque<Long> queue = new LinkedBlockingDeque<>();
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                if (!queue.isEmpty()) {
                    List<Long> values = Lists.newArrayList();
                    queue.drainTo(values);
                    Iterator<Long> iterator = values.iterator();
                    while (iterator.hasNext()) {
                        System.out.println(iterator.next());
                    }
                }
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();

        AtomicLong counter = new AtomicLong();

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                while (true) {
                    queue.offer(counter.incrementAndGet());
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
