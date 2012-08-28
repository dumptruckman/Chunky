package com.dumptruckman.minecraft.chunky.object.region;

import com.sk89q.worldedit.Vector2D;

public interface FlatRegion extends Region {

    /**
     * Gets the minimum Y value
     *
     * @return
     */
    public int getMinimumY();

    /**
     * Gets the maximum Y value
     *
     * @return
     */
    public int getMaximumY();

    public Iterable<Vector2D> asFlatRegion();
}
