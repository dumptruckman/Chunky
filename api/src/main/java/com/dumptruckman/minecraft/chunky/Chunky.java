package com.dumptruckman.minecraft.chunky;

import com.dumptruckman.minecraft.chunky.object.ChunkyWorld;
import com.dumptruckman.minecraft.chunky.util.WorldProvider;

public class Chunky {

    private static WorldProvider worldProvider;

    static {
        worldProvider = new DefaultWorldProvider();
    }

    public static ChunkyWorld getWorld(String world) {
        return worldProvider.getWorld(world);
    }

    public static void registerWorldProvider(WorldProvider worldProvider) {
        Chunky.worldProvider = worldProvider;
    }
}
