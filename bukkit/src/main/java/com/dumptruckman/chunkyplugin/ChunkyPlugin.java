package com.dumptruckman.chunkyplugin;

import com.dumptruckman.chunkyapi.plugin.Chunky;
import com.dumptruckman.chunkyplugin.config.Config;
import com.dumptruckman.chunkyplugin.config.YamlConfig;
import com.dumptruckman.minecraft.plugin.AbstractBukkitPlugin;
import com.dumptruckman.minecraft.config.Entries;

import java.io.IOException;

public class ChunkyPlugin extends AbstractBukkitPlugin<Config> implements Chunky {

    public void preEnable() {
        Entries.registerConfig(Config.class);
    }
    
    @Override
    public String getCommandPrefix() {
        return "chunky";
    }

    @Override
    protected Config newConfigInstance() throws IOException {
        return new YamlConfig(this);
    }
}
