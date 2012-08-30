/*
 * WorldEdit
 * Copyright (C) 2010, 2011 sk89q <http://www.sk89q.com> and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
*/
package com.dumptruckman.minecraft.chunky.object.region;

import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldedit.Vector2D;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FlatRegion3DIterator implements Iterator<BlockVector> {

    private Iterator<Vector2D> flatIterator;
    private int minY;
    private int maxY;

    private Vector2D next2D;
    private int nextY;

    public FlatRegion3DIterator(FlatRegion region, Iterator<Vector2D> flatIterator) {
        this.flatIterator = flatIterator;
        this.minY = region.getMinimumY();
        this.maxY = region.getMaximumY();

        if (flatIterator.hasNext()) {
            this.next2D = flatIterator.next();
        } else {
            this.next2D = null;
        }
        this.nextY = minY;
    }

    public FlatRegion3DIterator(FlatRegion region) {
        this(region, region.asFlatRegion().iterator());
    }

    @Override
    public boolean hasNext() {
        return next2D != null;
    }

    @Override
    public BlockVector next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        BlockVector current = new BlockVector(next2D.getBlockX(), nextY, next2D.getBlockZ());
        if (nextY < maxY) {
            nextY++;
        } else if (flatIterator.hasNext()) {
            next2D = flatIterator.next();
            nextY = minY;
        } else {
            next2D = null;
        }

        return current;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
