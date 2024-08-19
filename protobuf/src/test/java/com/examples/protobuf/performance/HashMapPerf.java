package com.examples.protobuf.performance;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author alex.fang
 * @date 2023/3/28
 */
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 2, time = 20)
@Threads(2)
@Fork(1)
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
/**
 * Benchmark                                   Mode  Cnt   Score   Error   Units
 * HashMapPerf.concurrent                      avgt    2   9.555           ns/op
 * HashMapPerf.concurrent:·gc.alloc.rate       avgt    2  ≈ 10⁻⁵          MB/sec
 * HashMapPerf.concurrent:·gc.alloc.rate.norm  avgt    2  ≈ 10⁻⁷            B/op
 * HashMapPerf.concurrent:·gc.count            avgt    2     ≈ 0          counts
 * HashMapPerf.lock                            avgt    2  12.737           ns/op
 * HashMapPerf.lock:·gc.alloc.rate             avgt    2  ≈ 10⁻⁵          MB/sec
 * HashMapPerf.lock:·gc.alloc.rate.norm        avgt    2  ≈ 10⁻⁷            B/op
 * HashMapPerf.lock:·gc.count                  avgt    2     ≈ 0          counts
 */
public class HashMapPerf {
    static final Map<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>();

    static final Map<Integer, Integer> hashMap = new HashMap<>();

    static final Lock lock = new ReentrantReadWriteLock().writeLock();

    @Benchmark
    public void lock(Blackhole blackhole) throws IOException {
        lock.lock();
        try {
            hashMap.put(1, 1);
        } finally {
            lock.unlock();
        }
    }


    @Benchmark
    public void concurrent(Blackhole blackhole) throws IOException {
        concurrentHashMap.put(1, 1);
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(HashMapPerf.class.getSimpleName())
                .result("result.json")
                .addProfiler(GCProfiler.class)
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }
}