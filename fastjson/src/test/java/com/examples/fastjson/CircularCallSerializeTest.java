package com.examples.fastjson;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author alex.fang
 * @date 2023/2/14
 */
class CircularCallSerializeTest {
    CircularCall c;

    @BeforeEach
    void setUp() {
        c = new CircularCall();
    }

    @Test
    void testSerialize() {
        assertThrows(StackOverflowError.class, () -> JSON.toJSONString(c));
    }
}
