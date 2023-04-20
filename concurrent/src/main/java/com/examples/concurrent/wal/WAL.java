package com.examples.concurrent.wal;

import java.util.ArrayList;
import java.util.List;

public class WAL {
    public long writeEntry(byte[] data) {
        return 0;
    }

    List<WALEntry> readAll() {
        return new ArrayList<>();
    }
}
