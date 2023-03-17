package com.examples.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author alex.fang
 * @date 2023/2/14
 */
class CircularReferenceSerializeTest {
    CircularReferenceA a;
    CircularReferenceB b;

    @BeforeEach
    void setUp() {
        a = new CircularReferenceA();
        b = new CircularReferenceB();
        a.setData(b);
        b.setData(a);
    }

    @Test
    void testSerializeWithCircularReferenceDetect() {
        String s = JSON.toJSONString(a);
        assertEquals(s, JSON.toJSONString(JSON.parseObject(s, CircularReferenceA.class)));
    }

    @Test
    void testSerializeWithoutCircularReferenceDetect() {
        assertThrows(StackOverflowError.class, () -> JSON.toJSONString(a, SerializerFeature.DisableCircularReferenceDetect));
    }
}