package com.dumptruckman.minecraft.chunky.object.region;

import com.sk89q.worldedit.BlockVector;

import java.util.Iterator;

public abstract class AbstractRegion implements Region {

    private String world;

    public AbstractRegion(String world) throws IllegalArgumentException {
        super();
        if (world == null) {
            throw new IllegalArgumentException("world may not be null");
        }
        this.world = world;
    }

    public AbstractRegion(int id) throws IllegalStateException, IllegalArgumentException {
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
    public AbstractRegion clone() throws CloneNotSupportedException {
        try {
            return (AbstractRegion) super.clone();
        } catch (CloneNotSupportedException exc) {
            return null;
        }
    }
}
