package com.dumptruckman.minecraft.chunky.persistence;

import com.avaje.ebean.validation.NotNull;
import com.dumptruckman.minecraft.chunky.json.JSONObject;

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
     * @return The name of this object.
     */
    @NotNull
    @Column(name = "NAME")
    public String getName();

    /**
     * Sets the name of this object to name.
     *
     * @param name New name for object.
     */
    @NotNull
    public void setName(String name);

    /**
     * @return A description of this object, may be blank.
     */
    @NotNull
    @Column(name = "DESC")
    public String getDescription();

    @NotNull
    public void setDescription(String description);

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
