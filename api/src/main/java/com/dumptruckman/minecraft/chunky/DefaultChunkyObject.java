package com.dumptruckman.minecraft.chunky;

import com.dumptruckman.minecraft.chunky.json.JSONException;
import com.dumptruckman.minecraft.chunky.object.ChunkyObject;
import com.dumptruckman.minecraft.chunky.persistence.JSONField;
import com.dumptruckman.minecraft.chunky.persistence.Table;

import java.sql.Timestamp;

@Table("ChunkyObject")
class DefaultChunkyObject extends DefaultPersistable implements ChunkyObject {

    @JSONField
    private String name;
    @JSONField
    private String description;

    DefaultChunkyObject(String className, long id, Timestamp lastUpdate, String jsonData, boolean newObject) throws JSONException, IllegalArgumentException {
        super(className, id, lastUpdate, jsonData, newObject);
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final void setName(String name) {
        this.name = name;
    }

    @Override
    public final String getDescription() {
        return description;
    }

    @Override
    public final void setDescription(String description) {
        this.description = description;
    }


}
