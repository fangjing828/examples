package com.examples.disruptor;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author alex.fang
 * @date 2023/3/17
 */
class SingleProducerAndMultiConsumerTest {
    @Test
    void test() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1000);

        int bufferSize = 8;
        Disruptor<LongEvent> disruptor =
                new Disruptor<>(LongEvent::new, bufferSize, DaemonThreadFactory.INSTANCE, ProducerType.SINGLE, new BlockingWaitStrategy());
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();
        BatchEventProcessor<LongEvent> batchEventProcessor1 =
                new BatchEventProcessor<>(ringBuffer, sequenceBarrier, (event, sequence, endOfBatch) -> {
                    System.out.println(Thread.currentThread().getName() + " " + "Event: " + event);
                    countDownLatch.countDown();
                });
        BatchEventProcessor<LongEvent> batchEventProcessor2 =
                new BatchEventProcessor<>(ringBuffer, sequenceBarrier, (event, sequence, endOfBatch) -> {
                    TimeUnit.MILLISECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + " " + "Event: " + event);
                    countDownLatch.countDown();
                });
        ringBuffer.addGatingSequences(batchEventProcessor1.getSequence());
        ringBuffer.addGatingSequences(batchEventProcessor2.getSequence());
        Executor executor = Executors.newFixedThreadPool(2, new ThreadFactory() {
            final AtomicInteger counter = new AtomicInteger();
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("Executor-" + counter.getAndIncrement());
                return t;
            }
        });
        executor.execute(batchEventProcessor1);
        executor.execute(batchEventProcessor2);
        disruptor.start();

        ByteBuffer bb = ByteBuffer.allocate(8);
        long count = countDownLatch.getCount();
        for (long l = 0; l < count; l++) {
            bb.putLong(0, l);
            ringBuffer.publishEvent((event, sequence, buffer) -> event.set(buffer.getLong(0)), bb);
        }
        assertTrue(countDownLatch.await(5, TimeUnit.SECONDS));
    }
}
