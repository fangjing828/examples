package com.examples.protobuf.performance;

import com.google.common.io.BaseEncoding;
import com.google.protobuf.ByteString;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by alex.fang on 2022/6/17.
 */
public class ProtobufUtils {
    public static Map<String, Object> toMap(MessageOrBuilder message) {
        Map<String, Object> result = new HashMap<>();
        getFields(message).forEach((k, v) -> {
            if (k.isMapField()) {
                result.put(k.getName(), toMapFieldValue(k, v));
            } else if (k.isRepeated()) {
                result.put(k.getName(), toRepeatedFieldValue(k, v));
            } else {
                result.put(k.getName(), toSingleFieldValue(k, v));
            }
        });
        return result;
    }

    static List<Object> toRepeatedFieldValue(Descriptors.FieldDescriptor field, Object value) {
        List list = (List) value;
        List<Object> result = new ArrayList<>(list.size());
        for (Object element : list) {
            result.add(toSingleFieldValue(field, element));
        }
        return result;
    }

    static Object toSingleFieldValue(
            final Descriptors.FieldDescriptor field, final Object value) {
        return switch (field.getType()) {
            case INT32, SINT32, SFIXED32 , BOOL, DOUBLE, UINT32, FIXED32, STRING -> value;
            case INT64, SINT64, SFIXED64, UINT64, FIXED64 -> Long.toString((long)value);
            case FLOAT -> Double.valueOf(value.toString());
            case BYTES -> BaseEncoding.base64().encode(((ByteString) value).toByteArray());
            case ENUM -> ((Descriptors.EnumValueDescriptor) value).getName();
            case MESSAGE, GROUP -> toMap((Message) value);
        };
    }

    static Map<Object, Object> toMapFieldValue(Descriptors.FieldDescriptor field, Object value) {
        Descriptors.Descriptor type = field.getMessageType();
        Descriptors.FieldDescriptor keyField = type.findFieldByName("key");
        Descriptors.FieldDescriptor valueField = type.findFieldByName("value");
        Map<Object, Object> result = new HashMap<>();
        Collection<Object> elements = (List<Object>) value;
        for (Object element : elements) {
            Message entry = (Message) element;
            Object entryKey = entry.getField(keyField);
            if (entryKey == null) {
                continue;
            }
            Object entryValue = entry.getField(valueField);
            result.put(entryKey.toString(),
                    toSingleFieldValue(valueField, entryValue));
        }
        return result;
    }

    static Map<Descriptors.FieldDescriptor, Object> getFields(MessageOrBuilder message) {
        Map<Descriptors.FieldDescriptor, Object>  fields = new TreeMap<>(message.getAllFields());
        for (Descriptors.FieldDescriptor field : message.getDescriptorForType().getFields()) {
            if (!fields.containsKey(field)) {
                fields.put(field, message.getField(field));
            }
        }
        return fields;
    }
}
