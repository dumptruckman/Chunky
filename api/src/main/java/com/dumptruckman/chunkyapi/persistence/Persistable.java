package com.dumptruckman.chunkyapi.persistence;

import com.dumptruckman.chunkyapi.json.JSONObject;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.sql.Timestamp;

/**
 * An interface describing an entry in a database.
 */
@MappedSuperclass
public interface Persistable {

    /**
     * @return The ID of this object for database persistence.
     */
    @Id
    @Column(name = "ID")
    long getId();

    /**
     * @return This instance's class name as a string.
     */
    @Column(name = "CLASS_NAME", nullable = false, length = 512)
    String getClassName();

    /**
     * @return The time the entry was last updated. (In the db?)
     */
    @Version
    @Column(name = "VERSION")
    Timestamp getLastUpdate();

    void setLastUpdate(Timestamp timestamp);

    JSONObject getData();

    @Column(name = "JSON_DATA", nullable = false)
    String jsonString();
    
    void load(String jsonString);
}
