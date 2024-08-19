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
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author alex.fang
 * @date 2023/3/28
 */
@BenchmarkMode({Mode.Throughput})
@Warmup(iterations = 3, time = 5)
@Measurement(iterations = 2, time = 10)
@Threads(1)
@Fork(10)
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class UUIDPerf {



    @Benchmark
    public void testJsonFormat(Blackhole blackhole) throws IOException {
        UUID.randomUUID().toString();
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(UUIDPerf.class.getSimpleName())
                .result("result.json")
                .addProfiler(GCProfiler.class)
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }
}
