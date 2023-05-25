package com.examples.concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class InterruptTest {
    @Test
    void testInterruptWithTimedWaiting() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                // Ignore
            }
        });
        thread.start();
        assetThreadInterruptAndNotContinuousInterrupt(thread, Thread.State.TIMED_WAITING);
    }

    @Test
    void testInterruptWithTimedWaitingAndContinuous() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        thread.start();
        assetThreadInterruptAndContinuousInterrupt(thread, Thread.State.TIMED_WAITING);
    }

    @Test
    void testInterruptWithWaiting() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread thread = new Thread(() -> {
            try {
                synchronized (this) {
                    this.wait();
                }
            } catch (InterruptedException e) {
                // Ignore
//                Thread.currentThread().interrupt();
            } finally {
                countDownLatch.countDown();
            }
        });
        thread.start();
        assetThreadInterruptAndNotContinuousInterrupt(thread, Thread.State.WAITING);

    }

    @Test
    void testInterruptWithWaitingAndContinuous() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                synchronized (this) {
                    this.wait();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
            }
        });
        thread.start();
        assetThreadInterruptAndContinuousInterrupt(thread, Thread.State.WAITING);
    }

    @Test
    void testInterruptWithBlocked() throws InterruptedException {
        Object lock = new Object();
        Thread t = new Thread(() -> {
            synchronized (lock) {
                try {
                    TimeUnit.MINUTES.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        t.start();
        Thread thread = new Thread(() -> {
            try {
                synchronized (lock) {
                    lock.wait(1);
                }
            } catch (InterruptedException e) {
                // Ignore
                Thread.currentThread().interrupt();
            }
        });
        thread.start();
        assetThreadInterruptFailed(thread, Thread.State.BLOCKED);
    }

    @Test
    void testInterruptWithRunnable() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                Thread.yield();
            }
        });
        thread.start();
        assetThreadInterruptFailed(thread, Thread.State.RUNNABLE);
    }

    @Test
    void testInterruptWithRunnableDetectAndContinuous() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                if (Thread.interrupted()) {
                    Thread.currentThread().interrupt();
                    break;
                }
                Thread.yield();
            }
        });
        thread.start();
        assetThreadInterruptAndContinuousInterrupt(thread, Thread.State.RUNNABLE);
    }

    @Test
    void testInterruptWithRunnableDetect() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                if (Thread.interrupted()) {
                    break;
                }
                Thread.yield();
            }
        });
        thread.start();
        assetThreadInterruptAndNotContinuousInterrupt(thread, Thread.State.RUNNABLE);
    }

    @Test
    void testInterruptBeforeStart() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                if (Thread.interrupted()) {
                    break;
                }
                Thread.yield();
            }
        });
        thread.interrupt();
        thread.start();
        while (!Thread.currentThread().isInterrupted()) {
            if (thread.getState() == Thread.State.TERMINATED) {
                break;
            }
            TimeUnit.MILLISECONDS.sleep(1);
        }
        assertEquals(Thread.State.TERMINATED, thread.getState());
    }

    @Test
    void testDuplicateInterrupt() throws InterruptedException {
        AtomicInteger counter = new AtomicInteger();
        Thread thread = new Thread(() -> {
            while (true) {
                if (Thread.interrupted()) {
                   counter.incrementAndGet();
                }
                Thread.yield();
            }
        });
        thread.start();
        int n = 10;
        for (int i = 0; i < n; i++) {
            thread.interrupt();
            TimeUnit.MILLISECONDS.sleep(1);
        }
        TimeUnit.MILLISECONDS.sleep(500);
        assertTrue(counter.get() >= 1);
        assertTrue(counter.get() <=  n);
    }

    /**
     * 线程中断成功，但没有继续传递中断
     */
    void assetThreadInterruptAndNotContinuousInterrupt(Thread thread, Thread.State expectedState) throws InterruptedException {
        assetThreadInterruptSuccess(thread, expectedState, false);
    }

    /**
     * 线程中断成功，且继续传递中断
     */
    void assetThreadInterruptAndContinuousInterrupt(Thread thread, Thread.State expectedState) throws InterruptedException {
        assetThreadInterruptSuccess(thread, expectedState, true);
    }

    /**
     * 线程中断成功
     */
    void assetThreadInterruptSuccess(Thread thread, Thread.State expectedState, boolean interrupted) throws InterruptedException {
        while (!Thread.currentThread().isInterrupted()) {
            if (thread.getState() == expectedState) {
                break;
            }
            TimeUnit.MILLISECONDS.sleep(1);
        }
        assertEquals(expectedState, thread.getState());

        thread.interrupt();
        long deadline = System.currentTimeMillis() + 1000;
        while (!Thread.currentThread().isInterrupted()) {
            if (thread.getState() == Thread.State.TERMINATED) {
                break;
            }
            if (System.currentTimeMillis() > deadline) {
                break;
            }
            TimeUnit.MILLISECONDS.sleep(1);
        }
        assertEquals(interrupted, thread.isInterrupted());
        assertEquals(Thread.State.TERMINATED, thread.getState());
    }

    /**
     * 线程中断失败
     */
    void assetThreadInterruptFailed(Thread thread, Thread.State expectedState) throws InterruptedException {
        while (!Thread.currentThread().isInterrupted()) {
            if (thread.getState() == expectedState) {
                break;
            }
            TimeUnit.MILLISECONDS.sleep(1);
            Thread.yield();
        }
        assertEquals(expectedState, thread.getState());

        thread.interrupt();
        long deadline = System.currentTimeMillis() + 1000;
        while (!Thread.currentThread().isInterrupted()) {
            if (thread.getState() == Thread.State.TERMINATED) {
                break;
            }
            if (System.currentTimeMillis() > deadline) {
                break;
            }
            TimeUnit.MILLISECONDS.sleep(1);
        }
        assertTrue(thread.isInterrupted());
        assertEquals(expectedState, thread.getState());
    }
}
