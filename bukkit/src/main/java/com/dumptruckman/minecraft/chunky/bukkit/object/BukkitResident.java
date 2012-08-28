package com.dumptruckman.minecraft.chunky.bukkit.object;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public interface BukkitResident extends com.dumptruckman.minecraft.chunky.object.Resident {

    Player getPlayer();

    OfflinePlayer getOfflinePlayer();
}
