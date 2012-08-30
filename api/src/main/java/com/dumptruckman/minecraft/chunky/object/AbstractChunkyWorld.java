package com.dumptruckman.minecraft.chunky.object;

public class AbstractChunkyWorld implements ChunkyWorld {

    private static final int MAX_Y = 255;

    private final String worldName;

    protected AbstractChunkyWorld(String worldName) {
        this.worldName = worldName;
    }

    @Override
    public String getName() {
        return worldName;
    }

    @Override
    public int getMaxHeight() {
        return MAX_Y;
    }
}
