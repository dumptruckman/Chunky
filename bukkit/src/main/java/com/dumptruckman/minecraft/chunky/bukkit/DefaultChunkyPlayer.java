package com.dumptruckman.minecraft.chunky.bukkit;

import com.dumptruckman.minecraft.chunky.bukkit.object.ChunkyPlayer;
import com.dumptruckman.minecraft.chunky.object.AbstractChunkyPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

class DefaultChunkyPlayer extends AbstractChunkyPlayer implements ChunkyPlayer {

    DefaultChunkyPlayer(String name) {
        super(name);
    }

    DefaultChunkyPlayer(String name, int id) {
        super(name, id);
    }

    @Override
    public Player getPlayer() {
        return Bukkit.getPlayer(getName());
    }
}
