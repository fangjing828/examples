package com.examples.concurrent.metric;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author alex.fang
 * @date 2023/2/27
 */
public class CounterBuffer {
    public final CounterBucket<Long>[] buckets;
    private final int bucketTimeWindow;
    private final int bufferTimeWindow;
    private final ReentrantLock lock = new ReentrantLock();
    private volatile int bucketIndex;

    public CounterBuffer(int numberOfBucket, int bucketTimeWindow) {
        this.buckets = new CounterBucket[numberOfBucket + 1];
        this.bucketTimeWindow = bucketTimeWindow;
        this.bufferTimeWindow = numberOfBucket * bucketTimeWindow;
        for (int i = 0; i < buckets.length; i++) {
            this.buckets[i] = new CounterBucket<>(0);
        }
    }

    public Map<Long, AtomicLong> count() {
        Map<Long, AtomicLong> result = new HashMap<>();
        long latestBucketTime = latestBucketTime();
        for (CounterBucket<Long> bucket : buckets) {
            if (includeInBufferTimeWindow(bucket, latestBucketTime)) {
                bucket.count().forEach((key, value) -> result.computeIfAbsent(key, k -> new AtomicLong()).addAndGet(value.get()));
            }
        }
        return result;
    }

    public void add(long userId) {
        bucket().incrementAndCount(userId);
    }

    CounterBucket<Long> bucket() {
        updateBucket();
        return buckets[bucketIndex];
    }

    void updateBucket() {
        long latestBucketTime = latestBucketTime();

        // 1. 桶的计数窗口包含 latestBucketTime，无需更新桶
        if (includeInBucketTimeWindow(buckets[bucketIndex], latestBucketTime)) {
            return;
        }

        // 2. 其它线程正在更新桶，无需更新桶
        if (!lock.tryLock()) {
            return;
        }

        try {
            // 3. 其它线程更新了桶，无需更新桶
            if (includeInBucketTimeWindow(buckets[bucketIndex], latestBucketTime)) {
                return;
            }

            // 4. 更新桶
            int nextBucketIndex = (bucketIndex + 1) % this.buckets.length;
            buckets[nextBucketIndex].reset(latestBucketTime);
            bucketIndex = nextBucketIndex;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 最新的桶（bucket）时间
     */
    long latestBucketTime() {
        long timeInMills = System.currentTimeMillis();
        // 时间取整
        return timeInMills - timeInMills % bucketTimeWindow;
    }

    /**
     * 当前 bucket 的计数窗口是否包含 bucketTime
     *
     * @param bucketTime bucket time
     * @return true 包含; false 不包含
     */
    boolean includeInBucketTimeWindow(CounterBucket<Long> bucket, long bucketTime) {
        return bucket.time() + bucketTimeWindow > bucketTime;
    }

    /**
     * 当前 bucket 的统计窗口是否包含 bucketTime
     *
     * @param bucketTime bucket time
     * @return true 包含; false 不包含
     */
    boolean includeInBufferTimeWindow(CounterBucket<Long> bucket, long bucketTime) {
        return (bucket.time() + bufferTimeWindow) > bucketTime;
    }
}
