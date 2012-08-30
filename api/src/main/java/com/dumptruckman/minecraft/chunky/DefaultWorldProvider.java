package com.dumptruckman.minecraft.chunky;

import com.dumptruckman.minecraft.chunky.object.AbstractChunkyWorld;
import com.dumptruckman.minecraft.chunky.object.ChunkyWorld;
import com.dumptruckman.minecraft.chunky.util.WorldProvider;

import java.util.HashMap;
import java.util.Map;

class DefaultWorldProvider implements WorldProvider {

    private final Map<String, ChunkyWorld> worlds = new HashMap<String, ChunkyWorld>();

    @Override
    public ChunkyWorld getWorld(String name) {
        if (worlds.containsKey(name)) {
            return worlds.get(name);
        } else {
            ChunkyWorld world = new AbstractChunkyWorld(name) { };
            worlds.put(name, world);
            return world;
        }
    }
}
