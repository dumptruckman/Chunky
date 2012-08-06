package com.dumptruckman.minecraft.chunky.object.region;

import com.dumptruckman.minecraft.chunky.object.ChunkyObject;
import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.Vector2D;

import java.util.Set;

public interface ChunkyRegion extends ChunkyObject, Iterable<BlockVector>, Cloneable {

    public Vector getMinimumPoint();

    public Vector getMaximumPoint();

    public int getArea();

    public int getWidth();

    public int getHeight();

    public int getLength();

    public boolean contains(Vector pt);

    public Set<Vector2D> getChunks();

    public Set<Vector> getChunkCubes();

    public String getWorld();

    public ChunkyRegion clone() throws CloneNotSupportedException;
}
