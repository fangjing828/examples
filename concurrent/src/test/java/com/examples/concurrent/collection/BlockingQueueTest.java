package com.examples.concurrent.collection;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BlockingQueueTest {
    /**
     * 单线程写有序数据到 BlockingQueue，单线程读取到的数据是有序的
     *
     */
    @Test
    void testBlockingQueueConcurrentReadAndWrite() throws InterruptedException {
        LinkedBlockingDeque<Long> queue = new LinkedBlockingDeque<>();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        // multi-write
        AtomicLong counter = new AtomicLong();
        Thread writeThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                queue.offer(counter.incrementAndGet());
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            countDownLatch.countDown();
        });
        writeThread.start();

        // read
        new Thread(() -> {
            long currentValue = 1;
            while (!Thread.currentThread().isInterrupted()) {
                if (!queue.isEmpty()) {
                    List<Long> values = Lists.newArrayList();
                    queue.drainTo(values);
                    for (Long value : values) {
                        System.out.println(value);
                        assertEquals(currentValue, value);
                        currentValue++;
                    }
                }
                if (currentValue > 300) {
                    writeThread.interrupt();
                    countDownLatch.countDown();
                    return;
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        assertTrue(countDownLatch.await(5, TimeUnit.SECONDS));
    }

    /**
     * 多线程有序数据到 BlockingQueue，单线程读取到的数据可能会乱序
     */
    @Test
    void testBlockingQueueConcurrentReadAndMultiWrite() throws InterruptedException {
        LinkedBlockingDeque<Long> queue = new LinkedBlockingDeque<>();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        AtomicLong counter = new AtomicLong();
        List<Thread> writeThreads = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    queue.offer(counter.incrementAndGet());
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                countDownLatch.countDown();
            });
            writeThreads.add(thread);
            thread.start();
        }

        // read
        new Thread(() -> {
            long currentValue = 1;
            while (!Thread.currentThread().isInterrupted()) {
                if (!queue.isEmpty()) {
                    List<Long> values = Lists.newArrayList();
                    queue.drainTo(values);
                    for (Long value : values) {
                        System.out.println(value);
                        if (currentValue != value) {
                            writeThreads.forEach(Thread::interrupt);
                            countDownLatch.countDown();
                            return;
                        }
                        currentValue++;
                    }
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        assertTrue(countDownLatch.await(5, TimeUnit.SECONDS));
    }
}
