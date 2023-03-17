package com.examples.disruptor;

public class LongEvent {
    private long value;

    public void set(long value) {
        this.value = value;
    }

    public void clear() {
        value = 0;
    }

    @Override
    public String toString() {
        return "LongEvent{" +
                "value=" + value +
                '}';
    }
}
