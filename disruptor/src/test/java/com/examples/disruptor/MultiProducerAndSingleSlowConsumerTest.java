package com.examples.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author alex.fang
 * @date 2023/3/17
 */
class MultiProducerAndSingleSlowConsumerTest {
    @Test
    void test() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1000);

        int bufferSize = 2;
        Disruptor<LongEvent> disruptor =
                new Disruptor<>(LongEvent::new, bufferSize, DaemonThreadFactory.INSTANCE);
        disruptor.handleEventsWith((event, sequence, endOfBatch) -> {
            TimeUnit.MILLISECONDS.sleep(1);
            System.out.println("Event: " + event);
            countDownLatch.countDown();
        });

        disruptor.start();
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        ByteBuffer bb = ByteBuffer.allocate(8);
        long count = countDownLatch.getCount();
        for (long l = 0; l < count; l++) {
            bb.putLong(0, l);
            ringBuffer.publishEvent((event, sequence, buffer) -> event.set(buffer.getLong(0)), bb);
        }
        assertTrue(countDownLatch.await(5, TimeUnit.SECONDS));
    }
}
