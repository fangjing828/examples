package com.examples.protobuf.performance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.util.JsonFormat;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex.fang on 2022/6/17.
 */
public class ProtobufUtils0 {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static Map<String, Object> toMap(MessageOrBuilder message) throws IOException {
        String result = JsonFormat.printer().print(message);
        return objectMapper.readValue(result, HashMap.class);
    }

    public static Map<String, Object> toMapAndIncludingDefaultValueFields(MessageOrBuilder message) throws IOException {
        String result = JsonFormat.printer()
                .includingDefaultValueFields()
                .print(message);
        return objectMapper.readValue(result, HashMap.class);
    }
}
