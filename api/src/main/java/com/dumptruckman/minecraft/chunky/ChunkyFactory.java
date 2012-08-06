package com.dumptruckman.minecraft.chunky;

import com.dumptruckman.minecraft.chunky.object.ChunkyObject;

import java.sql.Timestamp;

public final class ChunkyFactory {

    public static ChunkyObject newChunkyObject(Class<? extends ChunkyObject> clazz) {
        long newId = System.nanoTime();
        return new DefaultChunkyObject(clazz.getName(), newId, new Timestamp(newId));
    }

    public static ChunkyObject getChunkyObject(long id) {
        return null;
    }
}
