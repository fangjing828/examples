package com.examples.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author alex.fang
 * @date 2023/3/17
 */
class MultiProducerAndSingleBatchConsumerTest {
    ThreadLocal<List<LongEvent>> LOCAL_EVENT = ThreadLocal.withInitial(LinkedList::new);

    @Test
    void test() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1001);

        int bufferSize = 512;
        Disruptor<LongEvent> disruptor =
                new Disruptor<>(LongEvent::new, bufferSize, DaemonThreadFactory.INSTANCE);
        disruptor.handleEventsWith((event, sequence, endOfBatch) -> {

            LOCAL_EVENT.get().add(event);
            if (endOfBatch || LOCAL_EVENT.get().size() >= 100) {
                System.out.println("BatchSize:" + LOCAL_EVENT.get().size());
                for (LongEvent e : LOCAL_EVENT.get()) {
                    TimeUnit.MILLISECONDS.sleep(1);
                    System.out.println("Event: " + e);
                    countDownLatch.countDown();
                }
                LOCAL_EVENT.get().clear();
            }
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
