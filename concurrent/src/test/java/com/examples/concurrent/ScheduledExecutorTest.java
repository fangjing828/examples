package com.examples.concurrent;

import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ScheduledExecutorTest {
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

    /**
     * 测试 cancel 方法
     */
    @Test
    void testCancel() throws InterruptedException {
        CountDownLatch countDownLatch1 = new CountDownLatch(5);
        CountDownLatch countDownLatch2 = new CountDownLatch(6);

        ScheduledFuture future = executorService.scheduleWithFixedDelay(() -> {
            countDownLatch1.countDown();
            countDownLatch2.countDown();
        }, 100, 100, TimeUnit.MILLISECONDS);

        countDownLatch1.await();
        //终止定期 job
        future.cancel(false);
        assertFalse(await(countDownLatch2, 1, TimeUnit.SECONDS));
    }

    /**
     * 调度执行低时延任务，任务执行整体耗时符合预期
     * @throws InterruptedException
     */
    @Test
    void testScheduleWithFixDelay_whenTaskCostLessThanDelay() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        ScheduledFuture future = executorService.scheduleWithFixedDelay(() -> countDownLatch.countDown(), 0, 10, TimeUnit.MILLISECONDS);
        long startTime = System.currentTimeMillis();
        countDownLatch.await();
        long interval = System.currentTimeMillis() - startTime;
        assertEquals(1000, interval, 300);
        future.cancel(false);
    }

    /**
     * 调度执行高时延任务，任务执行整体耗时符合预期
     * @throws InterruptedException
     */
    @Test
    void testScheduleWithFixDelay_whenTaskCostGreaterThanDelay() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        ScheduledFuture future = executorService.scheduleWithFixedDelay(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            countDownLatch.countDown();
        }, 0, 10, TimeUnit.MILLISECONDS);
        long startTime = System.currentTimeMillis();
        countDownLatch.await();
        long interval = System.currentTimeMillis() - startTime;
        assertEquals(3000, interval, 900);
        future.cancel(false);
    }

    /**
     * 调度执行低时延任务，任务执行整体耗时符合预期
     * @throws InterruptedException
     */
    @Test
    void testScheduleWithFixRate_whenTaskCostLessThanRate() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        ScheduledFuture future = executorService.scheduleAtFixedRate(() -> countDownLatch.countDown(), 0, 10, TimeUnit.MILLISECONDS);

        long startTime = System.currentTimeMillis();
        countDownLatch.await();
        long interval = System.currentTimeMillis() - startTime;
        assertEquals(1000, interval, 300);
        future.cancel(false);
    }

    /**
     * 调度执行高时延任务，任务执行整体耗时符合预期
     * @throws InterruptedException
     */
    @Test
    void testScheduleWithFixRate_whenTaskCostGreaterThanRate() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        ScheduledFuture future = executorService.scheduleAtFixedRate(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            countDownLatch.countDown();
        }, 0, 10, TimeUnit.MILLISECONDS);
        long startTime = System.currentTimeMillis();
        countDownLatch.await();
        long interval = System.currentTimeMillis() - startTime;
        assertEquals(2000, interval, 600);
        future.cancel(false);
    }

    @Test
    public void testSchedule() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    countDownLatch.countDown();
                } finally {
                    executorService.schedule(this, 10, TimeUnit.MILLISECONDS);
                }
            }
        };
        executorService.schedule(r, 0, TimeUnit.MILLISECONDS);
        countDownLatch.await(1, TimeUnit.SECONDS);
    }

    boolean await(CountDownLatch countDownLatch, int timeout, TimeUnit timeUnit) {
        try {
            return countDownLatch.await(timeout, timeUnit);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

}
