package com.examples.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author alex.fang
 * @date 2023/3/17
 */
public class BigDecimalSerializerTest {
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    /**
     * 使用正常 {@link ObjectMapper} 序列换对象
     */
    @Test
    void testWriteValueAsString() throws JsonProcessingException {
        assertEquals("{\"number\":null}", objectMapper.writeValueAsString(new Origin()));
        assertEquals("{\"number\":1.7976931348623157E+308}", objectMapper.writeValueAsString(new Origin(BigDecimal.valueOf(Double.MAX_VALUE))));
    }

    /**
     * 使用定制 {@link BigDecimal} 序列化方式的 {@link ObjectMapper} 序列化对象
     */
    @Test
    void testWriteValueAsStringWithCustomBigDecimalSerializer1() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(BigDecimal.class, new BigDecimalSerializer());
        objectMapper.registerModule(module);

        assertEquals("{\"number\":null}", objectMapper.writeValueAsString(new Origin()));
        assertEquals("{\"number\":\"179000000000000000\"}", objectMapper.writeValueAsString(new Origin(BigDecimal.valueOf(178999999999999999.2))));
    }

    /**
     * 使用正常 {@link ObjectMapper} 序列化定制 {@link BigDecimal} 序列化方式的对象
     */
    @Test
    void testWriteValueAsStringWithCustomBigDecimalSerializer2() throws JsonProcessingException {
        assertEquals("{\"number\":null}", objectMapper.writeValueAsString(new OriginWithBigDecimalSerializer()));
        assertEquals("{\"number\":\"179000000000000000\"}", objectMapper.writeValueAsString(new OriginWithBigDecimalSerializer(BigDecimal.valueOf(178999999999999999.2))));
    }
}
