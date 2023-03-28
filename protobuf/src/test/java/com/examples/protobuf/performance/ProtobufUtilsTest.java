package com.examples.protobuf.performance;

import com.examples.protobuf.map.EnumType;
import com.examples.protobuf.map.ListMessageType;
import com.examples.protobuf.map.MapMessageType;
import com.examples.protobuf.map.MessageType;
import com.examples.protobuf.map.ReferenceMessageType;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author alex.fang
 * @date 2023/3/28
 */
class ProtobufUtilsTest {
    static final AtomicLong counter = new AtomicLong();

    static MessageType messageType = MessageType.newBuilder()
            .setBoolValue(true)
            .setStringValue("test")
            .setBytesValue(ByteString.copyFrom(new byte[]{'a', 'b', 'c'}))
            .setInt32Value(12)
            .setFixed32Value(13)
            .setUint32Value(14)
            .setInt64Value(15)
            .setFixed64Value(17)
            .setUint64Value(18)
            .setFloatValue(3.14f)
            .setDoubleValue(2.17)
            .setEnumValue(EnumType.A)
            .setReferenceValue(newReference()).build();

    static MapMessageType.Builder mapMessageType = MapMessageType.newBuilder()
            .putBoolValue(true, true)
            .putBoolValue(false, false)
            .putStringValue("key", "value")
            .putInt32Value(32, 32)
            .putFixed32Value(33, 33)
            .putUint32Value(34, 34)
            .putInt64Value(64, 64)
            .putFixed64Value(65, 65)
            .putUint64Value(66, 66)
            .putFloatValue("float", 3.14f)
            .putDoubleValue("double", 2.73)
            .putEnumValue("enum", EnumType.A)
            .putReferenceValue("reference", newReference());

    static ListMessageType listMessageType = ListMessageType.newBuilder()
            .addBoolValue(true)
            .addBoolValue(false)
            .addStringValue("abc")
            .addStringValue("def")
            .addInt32Value(32)
            .addInt32Value(33)
            .addFixed32Value(34)
            .addFixed32Value(35)
            .addUint32Value(36)
            .addUint32Value(37)
            .addInt64Value(64)
            .addInt64Value(65)
            .addFixed64Value(66)
            .addFixed64Value(67)
            .addUint64Value(68)
            .addUint64Value(69)
            .addFloatValue(3.14f)
            .addFloatValue(1.74f)
            .addDoubleValue(2.73)
            .addDoubleValue(0.95)
            .addEnumValue(EnumType.B)
            .addEnumValue(EnumType.D)
            .addReferenceValue(newReference())
            .addReferenceValue(newReference())
            .build();

    @Test
    void testMerge() throws InvalidProtocolBufferException {
        String string = "{\"boolValue\":true, \"boolValue_new\":true}";
        MessageType.Builder builder = MessageType.newBuilder();
        JsonFormat.parser().ignoringUnknownFields().merge(string, builder);
        assertEquals(JsonFormat.printer()
                        .omittingInsignificantWhitespace()
                        .includingDefaultValueFields()
                        .preservingProtoFieldNames()
                        .printingEnumsAsInts()
                        .sortingMapKeys()
                        .print(builder),
                "{\"boolValue\":true,\"stringValue\":\"\",\"bytesValue\":\"\",\"int32Value\":0,\"fixed32Value\":0,\"uint32Value\":0,\"int64Value\":\"0\",\"fixed64Value\":\"0\",\"uint64Value\":\"0\",\"floatValue\":0.0,\"doubleValue\":0.0,\"enumValue\":0}");
    }


    @Test
     void testBasicType() throws IOException {
        Map<String, Object> map0 = ProtobufUtils0.toMapAndIncludingDefaultValueFields(messageType);
        Map<String, Object> map = ProtobufUtils.toMap(messageType);
        assetMap(map0, map);
    }

    @Test
     void testBasicTypeWithDefault() throws IOException {
        MessageType.Builder builder = MessageType.newBuilder();
        Map<String, Object> map0 = ProtobufUtils0.toMapAndIncludingDefaultValueFields(builder);
        Map<String, Object> map = ProtobufUtils.toMap(builder);
        map.remove("referenceValue");
        assetMap(map0, map);

    }

    @Test
     void testMapType() throws IOException {
        Map<String, Object> map0 = ProtobufUtils0.toMapAndIncludingDefaultValueFields(mapMessageType);
        Map<String, Object> map = ProtobufUtils.toMap(mapMessageType);
        assetMap(map0, map);
    }

    @Test
     void testMapTypeWithDefault() throws IOException {
        MapMessageType.Builder builder = MapMessageType.newBuilder();
        Map<String, Object> map0 = ProtobufUtils0.toMapAndIncludingDefaultValueFields(builder);
        Map<String, Object> map = ProtobufUtils.toMap(builder);
        assetMap(map0, map);
    }

    @Test
     void testListType() throws IOException {
        Map<String, Object> map0 = ProtobufUtils0.toMapAndIncludingDefaultValueFields(listMessageType);
        Map<String, Object> map = ProtobufUtils.toMap(listMessageType);
        assetMap(map0, map);
    }

    @Test
     void testListTypeWithDefault() throws IOException {
        ListMessageType.Builder builder = ListMessageType.newBuilder();
        Map<String, Object> map0 = ProtobufUtils0.toMapAndIncludingDefaultValueFields(builder);
        Map<String, Object> map = ProtobufUtils.toMap(builder);
        assetMap(map0, map);
    }

    void assetMap(Map<String, Object> o1, Map<String, Object> o2) {
        assetMap("$", o1, o2);
    }

    void assetMap(String path, Map<String, Object> o1, Map<String, Object> o2) {
        assertEquals(o1.size(), o2.size());
        for (String key : o1.keySet()) {
            if (key.equals("timestampValue")) {
                continue;
            }
            Object value1 = o1.get(key);
            Object value2 = o2.get(key);
            String subPath = path + "." + key;
            if (value1 instanceof HashMap) {
                assetMap(subPath, (Map<String, Object>) value1, (Map<String, Object>) value2);
            } else if (value1 instanceof ArrayList<?>) {
                assertArrayEquals(((ArrayList<?>) value1).toArray(), ((ArrayList<?>) value2).toArray());
            } else {
                assertEquals(o1.get(key), o2.get(key), subPath);
            }
        }
    }


    private static ReferenceMessageType newReference() {
        return ReferenceMessageType.newBuilder()
                .setString("stringValue_" + counter.incrementAndGet())
                .addList("listValue_" + counter.incrementAndGet())
                .addList("listValue_" + counter.incrementAndGet())
                .putMap("key_" + counter.incrementAndGet(), "value_" + counter.incrementAndGet())
                .putMap("key_" + counter.incrementAndGet(), "value_" + counter.incrementAndGet())
                .build();
    }
}