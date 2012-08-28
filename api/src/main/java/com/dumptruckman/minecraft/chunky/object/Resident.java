package com.dumptruckman.minecraft.chunky.object;

import com.dumptruckman.minecraft.chunky.persistence.Persistable;

public interface Resident extends Persistable {

    /**
     * Gets the name of the player.
     *
     * @return The name of the player.
     */
    String getName();
}
