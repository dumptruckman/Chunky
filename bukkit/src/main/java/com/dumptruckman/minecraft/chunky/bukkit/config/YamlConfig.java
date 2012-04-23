package com.dumptruckman.minecraft.chunky.bukkit.config;

import com.dumptruckman.minecraft.chunky.config.ChunkyConfig;
import com.dumptruckman.minecraft.pluginbase.config.AbstractYamlConfig;
import com.dumptruckman.minecraft.pluginbase.config.ConfigEntry;
import com.dumptruckman.minecraft.pluginbase.config.EntryBuilder;
import com.dumptruckman.minecraft.pluginbase.plugin.BukkitPlugin;
import com.dumptruckman.minecraft.pluginbase.util.Null;

import java.io.File;
import java.io.IOException;

public class YamlConfig extends AbstractYamlConfig implements ChunkyConfig {

    private static final ConfigEntry<Null> SETTINGS = new EntryBuilder<Null>(Null.class, "settings")
            .comment("# Settings").build();

    public YamlConfig(BukkitPlugin plugin) throws IOException {
        super(plugin, true, new File(plugin.getDataFolder(), "config.yml"), ChunkyConfig.class);
    }

    @Override
    protected String getHeader() {
        return "# === [ Chunky Configuration ] ===";
    }
}
