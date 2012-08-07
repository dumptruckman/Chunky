package com.dumptruckman.minecraft.chunky.object.region;

import com.dumptruckman.minecraft.chunky.object.AbstractChunkyObject;
import com.dumptruckman.minecraft.chunky.persistence.JSONField;
import com.dumptruckman.minecraft.chunky.persistence.JSONPath;
import com.dumptruckman.minecraft.chunky.persistence.NotNull;
import com.dumptruckman.minecraft.chunky.persistence.Table;
import com.sk89q.worldedit.BlockVector;

import java.util.Iterator;

@Table("ChunkyRegion")
@JSONPath({"region"})
public abstract class AbstractChunkyRegion extends AbstractChunkyObject implements ChunkyRegion {

    @JSONField
    @NotNull
    private String world;

    public AbstractChunkyRegion(String world) throws /*JSONException,*/ IllegalArgumentException {
        super();
        if (world == null) {
            throw new IllegalArgumentException("world may not be null");
        }
        this.world = world;
    }

    public AbstractChunkyRegion(long id) throws /*JSONException,*/ IllegalStateException, IllegalArgumentException {
        super(id);
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
