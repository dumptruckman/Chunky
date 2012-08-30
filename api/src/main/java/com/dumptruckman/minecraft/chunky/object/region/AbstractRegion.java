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

import com.dumptruckman.minecraft.chunky.object.ChunkyWorld;
import com.sk89q.worldedit.BlockVector;

import java.util.Iterator;

public abstract class AbstractRegion implements Region {

    protected final ChunkyWorld world;

    public AbstractRegion(ChunkyWorld world) throws IllegalArgumentException {
        if (world == null) {
            throw new IllegalArgumentException("world may not be null");
        }
        this.world = world;
    }

    @Override
    public Iterator<BlockVector> iterator() {
        return new RegionIterator(this);
    }

    @Override
    public ChunkyWorld getWorld() {
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
