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
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.Vector2D;

import java.util.Set;

public interface Region extends Iterable<BlockVector>, Cloneable {

    public Vector getMinimumPoint();

    public Vector getMaximumPoint();

    public int getArea();

    public int getWidth();

    public int getHeight();

    public int getLength();

    public boolean contains(Vector pt);

    public Set<Vector2D> getChunks();

    public Set<Vector> getChunkCubes();

    public ChunkyWorld getWorld();

    public Region clone() throws CloneNotSupportedException;
}
