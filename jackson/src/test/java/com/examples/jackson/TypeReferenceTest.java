package com.examples.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author alex.fang
 * @date 2023/3/17
 */
public class TypeReferenceTest {
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void testReadValueAsList() throws JsonProcessingException {
        String jsonCarArray = "[{\"color\":\"Black\",\"type\":\"BMW\"},{\"color\":\"Red\",\"type\":\"FIAT\"}]";
        List<Car> list = objectMapper.readValue(jsonCarArray, new TypeReference<>() {
        });
        assertEquals(jsonCarArray, objectMapper.writeValueAsString(list));

        String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
        Map<String, Object> map = objectMapper.readValue(json, new TypeReference<>() {
        });
        assertEquals(map.get("color"), "Black");
        assertEquals(map.get("type"), "BMW");
    }

    @Test
    void testReadValueAsMap() throws JsonProcessingException {
        String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
        Map<String, Object> map = objectMapper.readValue(json, new TypeReference<>() {
        });
        assertEquals(map.get("color"), "Black");
        assertEquals(map.get("type"), "BMW");
    }
}
