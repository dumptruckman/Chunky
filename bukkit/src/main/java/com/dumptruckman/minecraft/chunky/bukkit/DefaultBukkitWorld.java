package com.dumptruckman.minecraft.chunky.bukkit;

import com.dumptruckman.minecraft.chunky.bukkit.object.BukkitWorld;
import com.dumptruckman.minecraft.chunky.object.AbstractChunkyWorld;
import org.bukkit.Bukkit;
import org.bukkit.World;

class DefaultBukkitWorld extends AbstractChunkyWorld implements BukkitWorld {

    private static final int MAX_Y = 255;

    DefaultBukkitWorld(String worldName) {
        super(worldName);
    }

    @Override
    public World getWorld() {
        return Bukkit.getWorld(getName());
    }

    @Override
    public int getMaxHeight() {
        World world = getWorld();
        return world == null ? MAX_Y : world.getMaxHeight();
    }
}
