package com.examples.protobuf.performance;

import com.google.protobuf.MessageOrBuilder;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
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
import java.util.concurrent.TimeUnit;

/**
 * @author alex.fang
 * @date 2023/3/28
 */
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 2, time = 20)
@Threads(4)
@Fork(1)
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ProtobufUtilsPerf {
    private MessageOrBuilder[] messages;

    @Setup
    public void init() throws IOException {
        messages = new MessageOrBuilder[] {
                ProtobufUtilsTest.messageType,
                ProtobufUtilsTest.listMessageType,
                ProtobufUtilsTest.mapMessageType
        };
    }

    @Benchmark
    public void testCustom(Blackhole blackhole) {
        for (MessageOrBuilder message : messages) {
            ProtobufUtils.toMap(message);
        }
        blackhole.consume(0);
    }

    @Benchmark
    public void testJsonFormat(Blackhole blackhole) throws IOException {
        for (MessageOrBuilder message : messages) {
            ProtobufUtils0.toMapAndIncludingDefaultValueFields(message);
        }
        blackhole.consume(0);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ProtobufUtilsPerf.class.getSimpleName())
                .result("result.json")
                .addProfiler(GCProfiler.class)
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }
}
