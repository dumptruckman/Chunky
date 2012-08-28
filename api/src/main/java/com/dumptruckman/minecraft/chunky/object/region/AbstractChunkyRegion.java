package com.dumptruckman.minecraft.chunky.object.region;

import com.sk89q.worldedit.BlockVector;

import java.util.Iterator;

public abstract class AbstractChunkyRegion implements ChunkyRegion {

    private String world;

    public AbstractChunkyRegion(String world) throws /*JSONException,*/ IllegalArgumentException {
        super();
        if (world == null) {
            throw new IllegalArgumentException("world may not be null");
        }
        this.world = world;
    }

    public AbstractChunkyRegion(int id) throws /*JSONException,*/ IllegalStateException, IllegalArgumentException {
        if (world == null) {
            throw new IllegalStateException("persisted world is null");
        }
    }

    @Override
    public Iterator<BlockVector> iterator() {
        return new RegionIterator(this);
    }

    @Override
    public String getWorld() {
        return world;
    }

    @Override
    public AbstractChunkyRegion clone() throws CloneNotSupportedException {
        try {
            return (AbstractChunkyRegion) super.clone();
        } catch (CloneNotSupportedException exc) {
            return null;
        }
    }
}
