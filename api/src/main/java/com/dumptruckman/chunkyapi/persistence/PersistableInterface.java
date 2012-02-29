package com.dumptruckman.chunkyapi.persistence;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

/**
 * An interface describing an entry in a database.
 */
@MappedSuperclass
public interface PersistableInterface {

    /**
     * @return The ID of this object for database persistence.
     */
    @Id
    public long getId();

    /**
     * @return This instance's class name as a string.
     */
    public String getClassName();

    /**
     * @return The time the entry was last updated. (In the db?)
     */
    public Timestamp getLastUpdate();
}
