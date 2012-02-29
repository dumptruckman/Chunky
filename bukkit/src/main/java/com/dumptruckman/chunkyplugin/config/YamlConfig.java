package com.dumptruckman.chunkyplugin.config;

import com.dumptruckman.minecraft.config.AbstractYamlConfig;
import com.dumptruckman.minecraft.config.ConfigEntry;
import com.dumptruckman.minecraft.config.Null;
import com.dumptruckman.minecraft.config.SimpleConfigEntry;
import com.dumptruckman.minecraft.plugin.BukkitPlugin;

import java.io.IOException;

public class YamlConfig extends AbstractYamlConfig implements Config {

    private static final ConfigEntry<Null> SETTINGS = new SimpleConfigEntry<Null>("settings", null, "# Settings");
    
    public YamlConfig(BukkitPlugin plugin) throws IOException {
        super(plugin);
    }

    @Override
    protected ConfigEntry getSettingsEntry() {
        return SETTINGS;
    }

    @Override
    protected String getHeader() {
        return "# === [ Chunky Configuration ] ===";
    }
}
