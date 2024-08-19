package com.examples.concurrent.perf;

import joptsimple.internal.Strings;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadModel {
    static int N = 1000;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            currentThread();
            singleThread();
            cachedThreadPool();
            fixedThreadPool();
            fixedThreadPoolX();
            singleThreadPool();
            System.out.println(Strings.repeat('+', 60));
        }
    }

    static void currentThread() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(N);
        Runnable task = newTask(countDownLatch);
        long start = System.nanoTime();
        for (int i = 0; i < N; i++) {
           task.run();
        }
        countDownLatch.await();
        System.out.println("currentThread:" + TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - start));
    }

    static void singleThread() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(N);
        Runnable task = newTask(countDownLatch);
        long start = System.nanoTime();
        for (int i = 0; i < N; i++) {
            new Thread(task).start();
        }
        countDownLatch.await();
        System.out.println("SingleThread:" + TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - start));
    }
    static void cachedThreadPool() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(N);
        Runnable task = newTask(countDownLatch);
        ExecutorService executor = Executors.newCachedThreadPool();
        long start = System.nanoTime();
        for (int i = 0; i < N; i++) {
            executor.submit(task);
        }
        countDownLatch.await();
        System.out.println("CachedThreadPool:" + TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - start));
        executor.shutdown();
    }
    static void fixedThreadPool() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(N);
        Runnable task = newTask(countDownLatch);
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        long start = System.nanoTime();
        for (int i = 0; i < N; i++) {
            executor.submit(task);
        }
        countDownLatch.await();
        System.out.println("FixThreadPool:" + TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - start));
        executor.shutdown();
    }
    static void fixedThreadPoolX() throws InterruptedException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CountDownLatch countDownLatch = new CountDownLatch(N);
        Runnable task = newTask(countDownLatch);
        executor.prestartAllCoreThreads();
        long start = System.nanoTime();
        for (int i = 0; i < N; i++) {
            executor.submit(task);
        }
        countDownLatch.await();
        System.out.println("FixThreadPoolX:" + TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - start));
        executor.shutdown();
    }

    static void singleThreadPool() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(N);
        Runnable task = newTask(countDownLatch);
        ThreadPoolExecutor[] executors = new ThreadPoolExecutor[8];
        for (int i = 0; i < 8; i++) {
            executors[i] = new ThreadPoolExecutor(1, 1,
                    0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>());
            executors[i].prestartAllCoreThreads();
        }
        long start = System.nanoTime();
        for (int i = 0; i < N; i++) {
            executors[i % 8].submit(task);
        }
        countDownLatch.await();
        System.out.println("singleThreadPool:" + TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - start));
        Arrays.stream(executors).forEach(e -> e.shutdown());
    }

    static Runnable newTask(CountDownLatch countDownLatch) {
        return () -> {
            countDownLatch.countDown();
        };
    }
}
