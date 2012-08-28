package com.dumptruckman.minecraft.chunky.persistence;

import java.sql.Timestamp;

public abstract class AbstractPersistable implements Persistable {

    private final Persistable persistable;

    protected AbstractPersistable() {
        this.persistable = Persistence.newPersistable();
    }

    protected AbstractPersistable(int id) {
        this.persistable = Persistence.newPersistable(id);
    }

    @Override
    public int getId() {
        return persistable.getId();
    }

    @Override
    public void setId(int id) {
        persistable.setId(id);
    }

    @Override
    public Timestamp getLastUpdate() {
        return persistable.getLastUpdate();
    }

    @Override
    public void setLastUpdate(Timestamp timestamp) {
        persistable.setLastUpdate(timestamp);
    }
}
