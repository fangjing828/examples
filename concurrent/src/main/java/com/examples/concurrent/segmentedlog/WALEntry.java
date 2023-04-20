package com.examples.concurrent.segmentedlog;

public class WALEntry {
    private long entryId;

    public long getEntryId() {
        return entryId;
    }

    public void setEntryId(long entryId) {
        this.entryId = entryId;
    }
}
