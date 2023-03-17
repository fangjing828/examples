package com.examples.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author alex.fang
 * @date 2023/2/14
 */
public class BigDecimalSerializer extends JsonSerializer<BigDecimal> {
    @Override
    public void serialize(BigDecimal bigDecimal, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        if (bigDecimal == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeString(bigDecimal.toPlainString());
        }
    }
}
