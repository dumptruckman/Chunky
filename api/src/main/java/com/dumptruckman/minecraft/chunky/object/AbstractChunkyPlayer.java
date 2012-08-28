package com.dumptruckman.minecraft.chunky.object;

import com.dumptruckman.minecraft.chunky.persistence.AbstractPersistable;

public abstract class AbstractChunkyPlayer extends AbstractPersistable implements ChunkyPlayer {

    private final String name;

    protected AbstractChunkyPlayer(String playerName) {
        this.name = playerName;
    }

    protected AbstractChunkyPlayer(String playerName, int id) {
        super(id);
        this.name = playerName;
    }

    @Override
    public final String getName() {
        return name;
    }
}
