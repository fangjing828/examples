package com.examples.disruptor;

import com.lmax.disruptor.ExceptionHandler;
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
class MultiProducerAndSingleExceptionConsumerTest {
    @Test
    void test() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1000);

        int bufferSize = 2;
        Disruptor<LongEvent> disruptor =
                new Disruptor<>(LongEvent::new, bufferSize, DaemonThreadFactory.INSTANCE);
        disruptor.handleEventsWith((event, sequence, endOfBatch) -> {
            countDownLatch.countDown();
            throw new IllegalStateException("不支持事件消费:" + event);
        });
        // 处理 disruptor 消费过程中抛出的异常
        disruptor.setDefaultExceptionHandler(new ExceptionHandler<LongEvent>() {
            @Override
            public void handleEventException(Throwable ex, long sequence, LongEvent event) {
                System.out.println("handleEventException");
                ex.printStackTrace();
            }

            @Override
            public void handleOnStartException(Throwable ex) {
                System.out.println("handleOnStartException");
                ex.printStackTrace();
            }

            @Override
            public void handleOnShutdownException(Throwable ex) {
                System.out.println("handleOnShutdownException");
                ex.printStackTrace();
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
