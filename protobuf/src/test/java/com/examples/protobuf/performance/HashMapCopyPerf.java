package com.examples.protobuf.performance;

import com.google.protobuf.MessageOrBuilder;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 2, time = 3)
@Threads(1)
@Fork(1)
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class HashMapCopyPerf {
    static final Map<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>();
    static final Map<Integer, Integer> hashMap = new HashMap<>();

    static {
        for (int i = 0; i < 100000; i++) {
            concurrentHashMap.put(i, i);
            hashMap.put(i, i);
        }
        System.out.println(hashMap.size());
    }

    @Benchmark
    public void hashMap(Blackhole blackhole) throws IOException {
      new ArrayList<>(hashMap.entrySet());
    }

    @Benchmark
    public void concurrent(Blackhole blackhole) throws IOException {
        new ArrayList<>(concurrentHashMap.entrySet());
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(HashMapCopyPerf.class.getSimpleName())
                .result("result.json")
                .addProfiler(GCProfiler.class)
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }
}
