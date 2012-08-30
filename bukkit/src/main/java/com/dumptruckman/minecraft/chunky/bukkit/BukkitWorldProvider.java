package com.dumptruckman.minecraft.chunky.bukkit;

import com.dumptruckman.minecraft.chunky.bukkit.object.BukkitWorld;
import com.dumptruckman.minecraft.chunky.util.WorldProvider;

import java.util.HashMap;
import java.util.Map;

class BukkitWorldProvider implements WorldProvider {

    private final Map<String, BukkitWorld> worlds = new HashMap<String, BukkitWorld>();

    @Override
    public BukkitWorld getWorld(String name) {
        if (worlds.containsKey(name)) {
            return worlds.get(name);
        } else {
            BukkitWorld world = new DefaultBukkitWorld(name);
            worlds.put(name, world);
            return world;
        }
    }
}
