package com.examples.concurrent.wal;

public interface Command {
    int SetValueType = 0;
    int RemoveValueType = 1;

    byte[] serialize();
}
