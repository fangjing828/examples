package com.examples.thread;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author alex.fang
 * @date 2023/4/3
 */
public class ThreadPoolExecutorTest {
    int taskSize = 50;

    @Test
    void threadPoolDeadLockWithSingleThread() {
        final ThreadPoolExecutor taskExecutor = newThreadPoolTaskExecutor(1, 1);
        CountDownLatch countDownLatch = new CountDownLatch(taskSize);
        new Thread(() -> {
            final List<CompletableFuture<Void>> completableFutures = IntStream.range(0, taskSize)
                    .boxed().map(workerIndex -> CompletableFuture.runAsync(() -> {
                        // 打印线程池的运行信息
                        printThreadPoolTaskExecutorInfo(workerIndex, taskExecutor);
                        try {
                            TimeUnit.MILLISECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        try {
                            System.out.println(">----开始任务 " + workerIndex);
                            taskExecutor.submit(System::currentTimeMillis).get();
                            System.out.println(">----结束任务 " + workerIndex);
                            countDownLatch.countDown();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }, taskExecutor).exceptionally(e -> null)).toList();
            completableFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
        }).start();

        assertThrows(IllegalStateException.class,
                () -> {
                    if (!countDownLatch.await(5, TimeUnit.SECONDS)) {
                        throw new IllegalStateException();
                    }
                });
    }
    /**
     * ThreadPool 执行死锁条件
     * 1. 线程池队列长度大于最大线程数
     * 2. 在同一个线程池内嵌套执行异步任务
     */
    @Test
    void threadPoolDeadLock() {
        final ThreadPoolExecutor taskExecutor = newThreadPoolTaskExecutor(2, 10);
        CountDownLatch countDownLatch = new CountDownLatch(taskSize);
        new Thread(() -> {
            final List<CompletableFuture<Void>> completableFutures = IntStream.range(0, taskSize)
                    .boxed().map(workerIndex -> CompletableFuture.runAsync(() -> {
                        // 打印线程池的运行信息
                        printThreadPoolTaskExecutorInfo(workerIndex, taskExecutor);
                        try {
                            TimeUnit.MILLISECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        try {
                            System.out.println(">----开始任务 " + workerIndex);
                            taskExecutor.submit(System::currentTimeMillis).get();
                            System.out.println(">----结束任务 " + workerIndex);
                            countDownLatch.countDown();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }, taskExecutor).exceptionally(e -> null)).toList();
            completableFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
        }).start();

        assertThrows(IllegalStateException.class,
                () -> {
                    if (!countDownLatch.await(5, TimeUnit.SECONDS)) {
                        throw new IllegalStateException();
                    }
                });
    }

    ThreadPoolExecutor newThreadPoolTaskExecutor(int corePoolSize, int maximumPoolSize) {
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(20),
                r -> {
                    Thread t = new Thread(r);
                    t.setName(ThreadPoolExecutorTest.class.getSimpleName());
                    return t;
                },
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    /**
     * 【打印线程池运行信息】
     */
    void printThreadPoolTaskExecutorInfo(int workerIndex, ThreadPoolExecutor taskExecutor) {
        System.out.printf("workerIndex:%d\n" +
                        "核心线程数：%d\n" +
                        "线程池大小：%d\n" +
                        "活跃线程数：%d\n" +
                        "线程保持时间（秒）：%d\n" +
                        "线程池最大数量：%d\n" +
                        "线程池等待的任务数量：%d\n" +
                        "线程池任务数量：%d\n",
                workerIndex,
                taskExecutor.getCorePoolSize(),
                taskExecutor.getPoolSize(),
                taskExecutor.getActiveCount(),
                taskExecutor.getKeepAliveTime(TimeUnit.SECONDS),
                taskExecutor.getMaximumPoolSize(),
                taskExecutor.getQueue().size(),
                taskExecutor.getTaskCount());
    }
}
