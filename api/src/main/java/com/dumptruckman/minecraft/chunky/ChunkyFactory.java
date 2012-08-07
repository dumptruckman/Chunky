package com.dumptruckman.minecraft.chunky;

import com.dumptruckman.minecraft.chunky.object.ChunkyObject;

import java.sql.Timestamp;

public final class ChunkyFactory {

    private static long lastId = 0;

    public static ChunkyObject newChunkyObject(Class<? extends ChunkyObject> clazz) throws /*JSONException,*/ IllegalArgumentException {
        long newId = System.nanoTime();
        while (newId == lastId) {
            newId = System.nanoTime();
        }
        lastId = newId;

        return new DefaultChunkyObject(clazz.getName(), newId, new Timestamp(newId), "{}", true);
    }

    public static ChunkyObject getChunkyObject(long id) {
        return null;
    }
}
