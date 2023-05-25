package com.examples.start.memory;

import java.nio.ByteBuffer;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试最大堆外内存配置
 */
public class DirectMemoryTest {
    /**
     * JDK 17.0.3-oracle，进程主动申请 10MB 堆外内存
     *
     * case 1: -XX:MaxDirectMemorySize=5m
     * Exception in thread "main" java.lang.OutOfMemoryError: Cannot reserve 10485760 bytes of direct buffer memory (allocated: 8192, limit: 5242880)
     *
     * case 2: -XX:MaxDirectMemorySize=10m
     * nothing
     *
     * case 3: -XX:MaxMetaspaceSize=5m
     * nothing
     *
     * case 4: -XX:MaxMetaspaceSize=10m
     * nothing
     */
    public static void main(String[] args) {
        int capacity = 1024 * 1024 * 10;
        ByteBuffer bb = ByteBuffer.allocateDirect(capacity);

        char c = 'A';
        int i = 123;
        bb.putChar(c);
        bb.putInt(i);
        assertEquals(0, bb.get());
        assertEquals(capacity, bb.limit());
        assertEquals(Character.BYTES + Integer.BYTES, bb.position() - 1);

        bb.position(0);
        assertEquals(c, bb.getChar());
        assertEquals(i, bb.getInt());
    }
}
