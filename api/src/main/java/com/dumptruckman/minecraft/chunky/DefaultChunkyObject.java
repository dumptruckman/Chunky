package com.dumptruckman.minecraft.chunky;

import com.dumptruckman.minecraft.chunky.object.ChunkyObject;

import java.sql.Timestamp;

class DefaultChunkyObject extends DefaultPersistable implements ChunkyObject {

    DefaultChunkyObject(String className, long id, Timestamp lastUpdate) {
        super(className, id, lastUpdate);
    }

    @Override
    public String getName() {
        return getData().optString("name");
    }

    @Override
    public void setName(String name) {
        getData().put("name", name);
    }

    @Override
    public String getDescription() {
        return getData().optString("description");
    }

    @Override
    public void setDescription(String description) {
        getData().put("description", description);
    }
}
