package com.dumptruckman.minecraft.chunky.object.region;

import com.dumptruckman.minecraft.chunky.json.JSONObject;
import com.dumptruckman.minecraft.chunky.object.AbstractChunkyObject;
import com.sk89q.worldedit.BlockVector;

import java.util.Iterator;

public abstract class AbstractChunkyRegion extends AbstractChunkyObject implements ChunkyRegion {

    private final String world;

    public AbstractChunkyRegion(String world) {
        super();
        if (world == null) {
            throw new IllegalArgumentException("world may not be null");
        }
        this.world = world;
        getData().put("world", world);
    }

    public AbstractChunkyRegion(long id) throws IllegalStateException, IllegalArgumentException {
        super(id);
        this.world = getData().optString("world");
        if (world == null) {
            throw new IllegalStateException("persisted world is null");
        }
    }

    @Override
    public JSONObject getData() {
        JSONObject ret = super.getData().optJSONObject("region");
        if (ret == null) {
            ret = new JSONObject();
            super.getData().put("region", ret);
        }
        return ret;
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
