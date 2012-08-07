package com.dumptruckman.minecraft.chunky.persistence;

import com.dumptruckman.minecraft.chunky.json.JSONObject;

import java.sql.Timestamp;

public interface Persistable {

    /**
     * @return The ID of this object for database persistence.
     */
    long getId();

    /**
     * @return This instance's class name as a string.
     */
    String getClassName();

    /**
     * @return The time the entry was last updated. (In the db?)
     */
    Timestamp getLastUpdate();

    void setLastUpdate(Timestamp timestamp);

    JSONObject getData();

    String jsonString();

    String getTable();

    void serialize();
}
