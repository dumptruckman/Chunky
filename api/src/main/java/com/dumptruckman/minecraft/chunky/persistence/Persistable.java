package com.dumptruckman.minecraft.chunky.persistence;

import java.sql.Timestamp;

public interface Persistable {

    /**
     * Gets the database row ID for this object.  An ID of -1 generally means that this object has not been
     * persisted to a database.
     *
     * @return The ID of this object for database persistence.
     */
    int getId();

    /**
     * Sets the database row ID for this object.
     *
     * @param id The database row ID for this object.
     */
    void setId(int id);

    /**
     * Gets the time at which this object was last updated in the database.
     *
     * @return The time the entry was last updated in the database.
     */
    Timestamp getLastUpdate();

    /**
     * Sets the time at which this object was last updated in the database.
     *
     * @param timestamp The time the entry was last updated in the database.
     */
    void setLastUpdate(Timestamp timestamp);
}
