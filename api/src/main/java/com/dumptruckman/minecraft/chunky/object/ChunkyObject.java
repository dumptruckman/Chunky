package com.dumptruckman.minecraft.chunky.object;

import com.dumptruckman.minecraft.chunky.persistence.Persistable;

public interface ChunkyObject extends Persistable {

    /**
     * @return The name of this object.
     */
    String getName();

    /**
     * Sets the name of this object to name.
     *
     * @param name New name for object.
     */
    void setName(String name);

    /**
     * @return A description of this object, may be blank.
     */
    String getDescription();

    void setDescription(String description);
}
