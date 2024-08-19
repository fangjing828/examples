package com.examples.cache.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
@BenchmarkMode({Mode.Throughput})
@Warmup(iterations = 3, time = 5)
@Measurement(iterations = 2, time = 10)
@Threads(1)
@Fork(10)
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class CachePerf {
    static final Cache<Long, Long> cache = CacheBuilder.newBuilder().expireAfterWrite(60, TimeUnit.SECONDS)
            .build();
    static {
        for (long i = 0; i < 10000; i++)  {
            cache.put(i, i);
        }
    }
    @Benchmark
    public void testGetIfPresent(Blackhole blackhole) throws IOException {
        cache.getIfPresent(ThreadLocalRandom.current().nextLong(10000));
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(CachePerf.class.getSimpleName())
                .result("result.json")
                .addProfiler(GCProfiler.class)
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }
}
