package com.dumptruckman.minecraft.chunky.object;

import com.dumptruckman.minecraft.chunky.json.JSONObject;

import java.sql.Timestamp;

public interface ChunkyObject {

    /**
     * @return The ID of this object for database persistence.
     */
    long getId();

    /**
     * @return This instance's class name as a string.
     */
    String getClassName();

    /**
     * @return The name of this object.
     */
    public String getName();

    /**
     * Sets the name of this object to name.
     *
     * @param name New name for object.
     */
    public void setName(String name);

    /**
     * @return A description of this object, may be blank.
     */
    public String getDescription();

    public void setDescription(String description);

    /**
     * @return The time the entry was last updated. (In the db?)
     */
    Timestamp getLastUpdate();

    void setLastUpdate(Timestamp timestamp);

    JSONObject getData();

    String jsonString();

    void load(String jsonString);
}
