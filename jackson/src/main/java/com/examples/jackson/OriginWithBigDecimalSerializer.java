package com.examples.jackson;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;

/**
 * @author alex.fang
 * @date 2023/2/14
 */
public class OriginWithBigDecimalSerializer {
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal number;

    public OriginWithBigDecimalSerializer() {
    }

    public OriginWithBigDecimalSerializer(BigDecimal number) {
        this.number = number;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public void setNumber(BigDecimal number) {
        this.number = number;
    }
}
