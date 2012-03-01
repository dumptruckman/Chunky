package com.dumptruckman.chunkyapi.object;

import com.avaje.ebean.validation.NotNull;
import com.dumptruckman.chunkyapi.persistence.Persistable;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public interface ChunkyIdentity extends Persistable {

    /**
     * @return The name of this object.
     */
    @NotNull
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
    public String getDescription();

    @NotNull
    public void setDescription(String description);
}
