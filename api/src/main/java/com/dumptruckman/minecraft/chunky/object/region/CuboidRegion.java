package com.dumptruckman.minecraft.chunky.object.region;

import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldedit.BlockVector2D;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.Vector2D;
import com.sk89q.worldedit.data.ChunkStore;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CuboidRegion extends AbstractRegion implements Region, FlatRegion {

    private Vector pos1;
    private Vector pos2;

    public CuboidRegion(String world, Vector pos1, Vector pos2) {
        super(world);
        this.pos1 = pos1;
        this.pos2 = pos2;
    }

    public CuboidRegion(int id) {
        super(id);
    }

    public CuboidRegion(com.sk89q.worldedit.regions.Region region) {
        this(region.getWorld().getName(), region.getMinimumPoint(), region.getMaximumPoint());
    }

    @Override
    public Vector getMinimumPoint() {
        return new Vector(Math.min(pos1.getX(), pos2.getX()),
                Math.min(pos1.getY(), pos2.getY()),
                Math.min(pos1.getZ(), pos2.getZ()));
    }

    @Override
    public Vector getMaximumPoint() {
        return new Vector(Math.max(pos1.getX(), pos2.getX()),
                Math.max(pos1.getY(), pos2.getY()),
                Math.max(pos1.getZ(), pos2.getZ()));
    }

    @Override
    public int getMinimumY() {
        return Math.min(pos1.getBlockY(), pos2.getBlockY());
    }

    @Override
    public int getMaximumY() {
        return Math.max(pos1.getBlockY(), pos2.getBlockY());
    }

    @Override
    public int getArea() {
        Vector min = getMinimumPoint();
        Vector max = getMaximumPoint();

        return (int)((max.getX() - min.getX() + 1) *
                (max.getY() - min.getY() + 1) *
                (max.getZ() - min.getZ() + 1));
    }

    @Override
    public int getWidth() {
        Vector min = getMinimumPoint();
        Vector max = getMaximumPoint();

        return (int) (max.getX() - min.getX() + 1);
    }

    @Override
    public int getHeight() {
        Vector min = getMinimumPoint();
        Vector max = getMaximumPoint();

        return (int) (max.getY() - min.getY() + 1);
    }

    @Override
    public int getLength() {
        Vector min = getMinimumPoint();
        Vector max = getMaximumPoint();

        return (int) (max.getZ() - min.getZ() + 1);
    }

    @Override
    public boolean contains(Vector pt) {
        double x = pt.getX();
        double y = pt.getY();
        double z = pt.getZ();

        Vector min = getMinimumPoint();
        Vector max = getMaximumPoint();

        return x >= min.getBlockX() && x <= max.getBlockX()
                && y >= min.getBlockY() && y <= max.getBlockY()
                && z >= min.getBlockZ() && z <= max.getBlockZ();
    }

    @Override
    public Set<Vector2D> getChunks() {
        Set<Vector2D> chunks = new HashSet<Vector2D>();

        Vector min = getMinimumPoint();
        Vector max = getMaximumPoint();

        for (int x = min.getBlockX(); x <= max.getBlockX(); ++x) {
            for (int z = min.getBlockZ(); z <= max.getBlockZ(); ++z) {
                chunks.add(new BlockVector2D(x >> ChunkStore.CHUNK_SHIFTS,
                        z >> ChunkStore.CHUNK_SHIFTS));
            }
        }

        return chunks;
    }

    @Override
    public Set<Vector> getChunkCubes() {
        Set<Vector> chunks = new HashSet<Vector>();

        Vector min = getMinimumPoint();
        Vector max = getMaximumPoint();

        for (int x = min.getBlockX(); x <= max.getBlockX(); ++x) {
            for (int y = min.getBlockY(); y <= max.getBlockY(); ++y) {
                for (int z = min.getBlockZ(); z <= max.getBlockZ(); ++z) {
                    chunks.add(new BlockVector(x >> ChunkStore.CHUNK_SHIFTS,
                            y >> ChunkStore.CHUNK_SHIFTS, z >> ChunkStore.CHUNK_SHIFTS));
                }
            }
        }

        return chunks;
    }

    @Override
    public Iterator<BlockVector> iterator() {
        return new Iterator<BlockVector>() {
            private Vector min = getMinimumPoint();
            private Vector max = getMaximumPoint();
            private int nextX = min.getBlockX();
            private int nextY = min.getBlockY();
            private int nextZ = min.getBlockZ();

            public boolean hasNext() {
                return (nextX != Integer.MIN_VALUE);
            }

            public BlockVector next() {
                if (!hasNext()) throw new java.util.NoSuchElementException();
                BlockVector answer = new BlockVector(nextX, nextY, nextZ);
                if (++nextX > max.getBlockX()) {
                    nextX = min.getBlockX();
                    if (++nextY > max.getBlockY()) {
                        nextY = min.getBlockY();
                        if (++nextZ > max.getBlockZ()) {
                            nextX = Integer.MIN_VALUE;
                        }
                    }
                }
                return answer;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public Iterable<Vector2D> asFlatRegion() {
        return new Iterable<Vector2D>() {
            @Override
            public Iterator<Vector2D> iterator() {
                return new Iterator<Vector2D>() {
                    private Vector min = getMinimumPoint();
                    private Vector max = getMaximumPoint();
                    private int nextX = min.getBlockX();
                    private int nextZ = min.getBlockZ();

                    public boolean hasNext() {
                        return (nextX != Integer.MIN_VALUE);
                    }

                    public Vector2D next() {
                        if (!hasNext()) throw new java.util.NoSuchElementException();
                        Vector2D answer = new Vector2D(nextX, nextZ);
                        if (++nextX > max.getBlockX()) {
                            nextX = min.getBlockX();
                            if (++nextZ > max.getBlockZ()) {
                                nextX = Integer.MIN_VALUE;
                            }
                        }
                        return answer;
                    }

                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    @Override
    public String toString() {
        return getMinimumPoint() + " - " + getMaximumPoint();
    }

    public CuboidRegion clone() throws CloneNotSupportedException {
        return (CuboidRegion) super.clone();
    }
}
