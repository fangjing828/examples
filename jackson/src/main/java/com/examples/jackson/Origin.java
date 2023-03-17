package com.examples.jackson;

import java.math.BigDecimal;

/**
 * @author alex.fang
 * @date 2023/2/14
 */
public class Origin {
    private BigDecimal number;

    public Origin() {
    }

    public Origin(BigDecimal number) {
        this.number = number;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public void setNumber(BigDecimal number) {
        this.number = number;
    }
}
