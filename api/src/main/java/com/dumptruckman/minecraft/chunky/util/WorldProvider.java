package com.dumptruckman.minecraft.chunky.util;

import com.dumptruckman.minecraft.chunky.object.ChunkyWorld;

public interface WorldProvider {

    ChunkyWorld getWorld(String name);
}
