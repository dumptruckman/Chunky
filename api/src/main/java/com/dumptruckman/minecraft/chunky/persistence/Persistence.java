package com.dumptruckman.minecraft.chunky.persistence;

import java.sql.Timestamp;

public class Persistence implements Persistable {

    public static Persistable newPersistable() {
        return new Persistence();
    }

    public static Persistable newPersistable(int id) {
        return new Persistence(id);
    }

    private Persistence() {
        id = -1;
        lastUpdate = null;
    }

    private Persistence(final int id) {
        this.id = id;
        lastUpdate = null;
    }

    private int id;
    private Timestamp lastUpdate;

    @Override
    public final int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public final Timestamp getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public final void setLastUpdate(Timestamp timestamp) {
        lastUpdate = timestamp;
    }
}
