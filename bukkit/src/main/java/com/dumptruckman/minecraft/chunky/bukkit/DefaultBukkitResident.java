package com.dumptruckman.minecraft.chunky.bukkit;

import com.dumptruckman.minecraft.chunky.bukkit.object.BukkitResident;
import com.dumptruckman.minecraft.chunky.object.AbstractResident;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

class DefaultBukkitResident extends AbstractResident implements BukkitResident {

    DefaultBukkitResident(String name) {
        super(name);
    }

    DefaultBukkitResident(String name, int id) {
        super(name, id);
    }

    @Override
    public Player getPlayer() {
        return Bukkit.getPlayer(getName());
    }

    @Override
    public OfflinePlayer getOfflinePlayer() {
        return Bukkit.getOfflinePlayer(getName());
    }
}
