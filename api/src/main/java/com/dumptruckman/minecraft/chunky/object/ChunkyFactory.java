package com.dumptruckman.minecraft.chunky.object;

import java.sql.Timestamp;

public final class ChunkyFactory {

    public static ChunkyObject newChunkyObject(Class<? extends ChunkyObject> clazz) {
        long newId = System.nanoTime();
        return new DefaultChunkyObject(clazz.getName(), newId, new Timestamp(newId));
    }
}
