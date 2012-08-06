package com.dumptruckman.minecraft.chunky.bukkit.util;

import com.sk89q.worldedit.LocalWorld;
import org.bukkit.Bukkit;
import org.bukkit.World;

public class BukkitUtil {

    private BukkitUtil() {
        throw new AssertionError();
    }

    public static LocalWorld getLocalWorld(String worldName) {
        World world = Bukkit.getWorld(worldName);
        if (world == null) {
            return null;
        }
        return com.sk89q.worldedit.bukkit.BukkitUtil.getLocalWorld(world);
    }
}
