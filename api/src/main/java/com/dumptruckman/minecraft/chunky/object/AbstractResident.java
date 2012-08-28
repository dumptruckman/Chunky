package com.dumptruckman.minecraft.chunky.object;

import com.dumptruckman.minecraft.chunky.persistence.AbstractPersistable;

public abstract class AbstractResident extends AbstractPersistable implements Resident {

    private final String name;

    protected AbstractResident(String playerName) {
        this.name = playerName;
    }

    protected AbstractResident(String playerName, int id) {
        super(id);
        this.name = playerName;
    }

    @Override
    public final String getName() {
        return name;
    }
}
