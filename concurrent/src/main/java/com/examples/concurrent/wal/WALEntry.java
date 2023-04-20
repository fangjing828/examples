package com.examples.concurrent.wal;

public class WALEntry {
    private final long entryId;
    private final byte[] data;
    private final EntryType entryType;
    private long timestamp;

    public WALEntry(long entryId, byte[] data, EntryType entryType) {
        this.entryId = entryId;
        this.data = data;
        this.entryType = entryType;
    }

    public long getEntryId() {
        return entryId;
    }

    public byte[] getData() {
        return data;
    }

    public EntryType getEntryType() {
        return entryType;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
