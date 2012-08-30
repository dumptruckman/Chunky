package com.dumptruckman.minecraft.chunky.bukkit;

import com.dumptruckman.minecraft.chunky.Chunky;
import com.dumptruckman.minecraft.chunky.ChunkyPlugin;
import com.dumptruckman.minecraft.chunky.bukkit.config.YamlConfig;
import com.dumptruckman.minecraft.chunky.bukkit.util.Language;
import com.dumptruckman.minecraft.chunky.config.ChunkyConfig;
import com.dumptruckman.minecraft.pluginbase.plugin.AbstractBukkitPlugin;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ChunkyBukkit extends AbstractBukkitPlugin<ChunkyConfig> implements ChunkyPlugin {

    private static final List<String> cmdPrefixes = Arrays.asList("chunky");
    private static final BukkitWorldProvider worldProvider = new BukkitWorldProvider();

    @Override
    public List<String> getCommandPrefixes() {
        return cmdPrefixes;
    }

    @Override
    protected ChunkyConfig newConfigInstance() throws IOException {
        return new YamlConfig(this);
    }

    @Override
    public void preEnable() {
        Language.init();
        Chunky.registerWorldProvider(worldProvider);
    }
}
