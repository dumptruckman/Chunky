package com.dumptruckman.chunkyplugin.config;

import com.dumptruckman.minecraft.config.BaseConfig;
import com.dumptruckman.minecraft.config.ConfigEntry;
import com.dumptruckman.minecraft.config.SimpleConfigEntry;

public interface Config extends BaseConfig {

    ConfigEntry<Integer> DATA_SAVE_PERIOD = new SimpleConfigEntry<Integer>("settings.data.save_every", 30, "# This is often module data is written to the disk.");
    ConfigEntry<Boolean> DEBUG = new SimpleConfigEntry<Boolean>("settings.debug_mode", false, "# Enables debugging mode");

    ConfigEntry<Boolean> USING_MYSQL = new SimpleConfigEntry<Boolean>("settings.mysql.using_mysql", true, "# True for MySQL, flat-files otherwise.");
    ConfigEntry<String> MYSQL_USERNAME = new SimpleConfigEntry<String>("settings.mysql.username", "root", "# Username for MySQL database.");
    ConfigEntry<String> MYSQL_PASSWORD = new SimpleConfigEntry<String>("settings.mysql.password", "password", "# Password for MySQL database.");
    ConfigEntry<String> MYSQL_HOST = new SimpleConfigEntry<String>("settings.mysql.host", "localhost", "# Address for the MySQL server.");
    ConfigEntry<String> MYSQL_DATABASE = new SimpleConfigEntry<String>("settings.mysql.database", "minecraft", "# Name of database to use.");
    ConfigEntry<String> MYSQL_PORT = new SimpleConfigEntry<String>("settings.mysql.port", "3306", "# MySQL server port.");

    ConfigEntry<String> SWITCH_IDS = new SimpleConfigEntry<String>("settings.switch_ids", "25,54,61,62,64,69,70,71,72,77,96,84,93,94", "# Switchable blocks");
    ConfigEntry<String> ITEM_USE_IDS = new SimpleConfigEntry<String>("settings.item_use_ids", "259,325,326,327,351", "# Usable items");

    ConfigEntry<Boolean> CHUNK_NAMED_AFTER_OWNER = new SimpleConfigEntry<Boolean>("chunks.name.named_after_owner", false, "# If true, this will default a chunk's name to the owner's name.", "# If false, a chunk's default name will be blank.");
    ConfigEntry<String> CHUNK_NAME_FORMAT_STRING = new SimpleConfigEntry<String>("chunks.name.format", "&f{%1: }\\~/%2",
                                     "# You may use color codes here.  Precede them with a & symbol.",
                                     "# %1 will be substituted with the Chunk name while %2 will be substituted with the Owner's name.",
                                     "# Anything between {} will only be displayed if the Chunk's name IS NOT blank.",
                                     "# Anything between \\/ will only be displayed if the Chunk's name IS blank.",
                                     "# If you leave out the owner name, you may want to enable named_after_owner.");

    ConfigEntry<Integer> PLAYER_CHUNK_LIMIT = new SimpleConfigEntry<Integer>("player.chunk_limit.default", 10, "# The default number of chunks a player list allowed to claim.");

    ConfigEntry<Boolean> UNOWNED_BUILD = new SimpleConfigEntry<Boolean>("unowned.build", false, "# Can player build on unowned chunks");
    ConfigEntry<Boolean> UNOWNED_DESTROY = new SimpleConfigEntry<Boolean>("unowned.destroy", false, "# Can player destroy on unowned chunks");
    ConfigEntry<Boolean> UNOWNED_ITEMUSE = new SimpleConfigEntry<Boolean>("unowned.item_use", false, "# Can player use items on unowned chunks");
    ConfigEntry<Boolean> UNOWNED_SWITCH = new SimpleConfigEntry<Boolean>("unowned.switch", false, "# Can player switch on unowned chunks");

    ConfigEntry<Boolean> WORLD_ENABLED = new SimpleConfigEntry<Boolean>("worlds.enabled_for_new_worlds", true, "# Whether or not chunky should be enabled for new worlds or not");
}
