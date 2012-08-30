package com.dumptruckman.minecraft.chunky.bukkit.object;

import com.dumptruckman.minecraft.chunky.object.ChunkyWorld;
import org.bukkit.World;

public interface BukkitWorld extends ChunkyWorld {

    World getWorld();
}
