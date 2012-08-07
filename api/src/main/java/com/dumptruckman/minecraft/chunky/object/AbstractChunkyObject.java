package com.dumptruckman.minecraft.chunky.object;

import com.dumptruckman.minecraft.chunky.ChunkyFactory;
import com.dumptruckman.minecraft.chunky.json.JSONObject;
import com.dumptruckman.minecraft.chunky.persistence.Table;

import java.sql.Timestamp;

@Table("ChunkyObject")
public class AbstractChunkyObject implements ChunkyObject {

    private final ChunkyObject chunkyObject;

    public AbstractChunkyObject() throws /*JSONException,*/ IllegalArgumentException {
        chunkyObject = ChunkyFactory.newChunkyObject(this.getClass());
    }

    public AbstractChunkyObject(long id) throws /*JSONException,*/ IllegalStateException, IllegalArgumentException {
        chunkyObject = ChunkyFactory.getChunkyObject(id);
        if (chunkyObject == null) {
            throw new IllegalArgumentException("There is no object with id: " + id);
        }
    }

    @Override
    public String getName() {
        return chunkyObject.getName();
    }

    @Override
    public void setName(String name) {
        chunkyObject.setName(name);
    }

    @Override
    public String getDescription() {
        return chunkyObject.getDescription();
    }

    @Override
    public void setDescription(String description) {
        chunkyObject.setDescription(description);
    }

    @Override
    public long getId() {
        return chunkyObject.getId();
    }

    @Override
    public String getClassName() {
        return chunkyObject.getClassName();
    }

    @Override
    public Timestamp getLastUpdate() {
        return chunkyObject.getLastUpdate();
    }

    @Override
    public void setLastUpdate(Timestamp timestamp) {
        chunkyObject.setLastUpdate(timestamp);
    }

    @Override
    public JSONObject getData() {
        return chunkyObject.getData();
    }

    @Override
    public String jsonString() {
        return chunkyObject.jsonString();
    }

    @Override
    public String getTable() {
        return chunkyObject.getTable();
    }

    @Override
    public void serialize() {
        chunkyObject.serialize();
    }
}
