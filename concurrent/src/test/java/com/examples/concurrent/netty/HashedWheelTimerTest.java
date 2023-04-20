package com.examples.concurrent.netty;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HashedWheelTimerTest {
    @Test
    void test() throws InterruptedException {
        HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(1, TimeUnit.SECONDS);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        hashedWheelTimer.newTimeout(new TimerTask() {
            @Override
            public void run(Timeout timeout) throws Exception {
                System.out.println(new Date());
                countDownLatch.countDown();
            }
        }, 5, TimeUnit.SECONDS);

        assertTrue(countDownLatch.await(5, TimeUnit.SECONDS));
    }
}
