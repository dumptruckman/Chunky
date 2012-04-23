package com.dumptruckman.minecraft.chunky.bukkit.util;

import com.dumptruckman.minecraft.pluginbase.locale.Message;

public class Language {

    public static void init() { }

    /**
     * Generic
     */
    public static final Message IN_GAME_ONLY = new Message("generic.in_game_only", "Only in game players may use this feature!");
    public static final Message ERROR = new Message("generic.error", "[Error]");
    public static final Message SUCCESS = new Message("generic.success", "[Success]");
    public static final Message HELP = new Message("generic.help", "[Help]");
    public static final Message INFO = new Message("generic.info", "[Info]");
    public static final Message YOUR_PROPERTY = new Message("generic.your_property", "your property");
    public static final Message ALL_SPECIFIC_PLAYERS = new Message("generic.all_specific_players", "all specific players");
    public static final Message ALL_THEIR_CURRENT_PROPERTY = new Message("generic.all_their_current_property", "all their current property");
    public static final Message YOUR_CURRENT_PROPERTY = new Message("generic.your_current_property", "your current property");
    public static final Message SOMEONES_PROPERTY = new Message("generic.someones_property", "%1's property");
    public static final Message THIS_CHUNK = new Message("generic.this_chunk", "this chunk");
    public static final Message EVERYONE = new Message("generic.everyone", "everyone");
    public static final Message THEIR_PROPERTY = new Message("generic.their_property", "their property");
    public static final Message NO_ONE = new Message("generic.no_one", "no one");
    public static final Message CHUNK_AT = new Message("generic.chunk_at", "chunk at [%1]");
    public static final Message FEATURE_NYI = new Message("feature_nyi", "Sorry, that feature is not yet implemented.");

    /**
     * Command
     */
    public static final Message NO_COMMAND_PERMISSION = new Message("command.no_permission", "You do not have permission to access this command!");
    public static final Message WORLD_DISABLED = new Message("command.world_disabled", "Chunky is diabled in this world!");
    public static final Message CMD_HELP = new Message("command.help", "Help for command: %1 (%2)");
    public static final Message CMD_LIST = new Message("command.list", "Sub-command list for: %1 (%2)");
    // Chunky
    public static final Message CMD_CHUNKY_DESC = new Message("command.chunky.description",
            "&e=== Chunky Information ===",
            "&fVersion: %1", "Loaded Modules - %2",
            "You can use &ehelp&f or &e?&f after chunky sub-commands",
            "&e/chunky <sub-cmd> help&f and &e/chunky <sub-cmd> ?&f",
            "Try typing &6/chunky help");
    public static final Message CMD_CHUNKY_HELP = new Message("command.chunky.help",
            "This command contains the main functions of Chunky.",
            "Type &6/chunky ? &fto see a list of sub-commands!");
    // Chunky Claim
    public static final Message CMD_CHUNKY_CLAIM_DESC = new Message("command.chunky.claim.description", "Claims the chunk the user stands in.");
    public static final Message CMD_CHUNKY_CLAIM_HELP = new Message("command.chunky.claim.help", "Claims the chunk you are standing in.");
    // Chunky Unclaim
    public static final Message CMD_CHUNKY_UNCLAIM_DESC = new Message("command.chunky.unclaim.description", "Unclaims the chunk the user stands in.");
    public static final Message CMD_CHUNKY_UNCLAIM_HELP = new Message("command.chunky.unclaim.help", "Unclaims the chunk you are standing in.");
    // Chunky Permission
    public static final Message CMD_CHUNKY_PERMISSION_DESC = new Message("command.chunky.permission.description", "Allows you to set permissions for other players and eventually groups.");
    public static final Message CMD_CHUNKY_PERMISSION_HELP = new Message("command.chunky.permission.help",
            "&eUsage: /chunky permission <this|all|global> <[<+|->flags]|[clear]> <name|all|global>",
            "&ethis: &fPermissions for chunk you are in.",
            "&eall: &fPermissions for all your current chunks.",
            "&eglobal: &fPermissions for all your current and any new chunks.",
            "&eflags: &fType of permission to give. Explained below. Separate each with comma.",
            "&eb: &fbuild   &ed: &fdestroy   &ei: &fitem use   &es: &fswitch",
            "&eclear: &fremoves all flags. (use instead of +/-flags)",
            "&ename: &fName of player to given permission to.",
            "&eall: &fAny players you have set permissions specifically for.",
            "&eglobal: &fPermissions for everyone else. (un-set players)",
            "&eExample: &f'&e/c p all +b,d dumptruckman&f' adds build/destroy permission for all your currently owned chunks to dumptruckman");
    public static final Message CMD_CHUNKY_PERMISSION_ADD_SUBTRACT = new Message("command.chunky.permission.add_subtract", "You must specify whether to allow or deny these flags with a + or -.");
    public static final Message CMD_CHUNKY_PERMISSION_HELP_REMINDER = new Message("command.chunky.permission.help_reminder", "Type &e/chunky permission help&f for help.");
    public static final Message CMD_CHUNKY_PERMISSION_SPECIFY_FLAGS = new Message("command.chunky.permission.specify_flags", "Make sure to specify the flags! (Example: +b,d,i,s)");
    // Chunky Player
    public static final Message CMD_CHUNKY_PLAYER_DESC = new Message("command.chunky.player.description", "Contains sub-commands related to players and info regarding yourself");
    public static final Message CMD_CHUNKY_PLAYER_HELP = new Message("command.chunky.player.help", "This command will tell you a little about yourself and contains some sub-commands related to players.");
    // Chunky Player Set
    public static final Message CMD_CHUNKY_PLAYER_SET_DESC = new Message("command.chunky.player.set.description", "Sets properties for you.");
    public static final Message CMD_CHUNKY_PLAYER_SET_HELP = new Message("command.chunky.player.set.help", "Type &e/chunky player set ?&f to see a list of what you can set.");
    // Chunky Player Set Mode
    public static final Message CMD_CHUNKY_PLAYER_SET_MODE_DESC = new Message("command.chunky.player.set.mode.description", "Setup modes for you.");
    public static final Message CMD_CHUNKY_PLAYER_SET_MODE_HELP = new Message("command.chunky.player.set.mode.help",
            "Allows you to set modes on yourself.",
            "Modes available: &eclaim&f   claim as you walk, &eclear&f   turns off modes");
    // Chunky Chunk
    public static final Message CMD_CHUNKY_CHUNK_DESC = new Message("command.chunky.chunk.description", "Information about the chunk you are in.");
    public static final Message CMD_CHUNKY_CHUNK_HELP = new Message("command.chunky.chunk.help", "This command will tell you a little about the chunk you are standing in.");
    // Chunky Chunk Set
    public static final Message CMD_CHUNKY_CHUNK_SET_DESC = new Message("command.chunky.chunk.set.description", "Sets properties for current chunk");
    public static final Message CMD_CHUNKY_CHUNK_SET_HELP = new Message("command.chunky.chunk.set.help", "Type &e/chunky chunk set ?&f to see a list of what you can set.");
    // Chunky Chunk Set Name
    public static final Message CMD_CHUNKY_CHUNK_SET_NAME_DESC = new Message("command.chunky.chunk.set.name.description", "Changes the name of the current chunk.");
    public static final Message CMD_CHUNKY_CHUNK_SET_NAME_HELP = new Message("command.chunky.chunk.set.name.help", "Usage: &e/chunky chunk set name <name> &f names the chunk <name>");
    // Chunky Group
    public static final Message CMD_CHUNKY_GROUP_DESC = new Message("command.chunky.group.description", "Contains commands for managing groups.");
    public static final Message CMD_CHUNKY_GROUP_HELP = new Message("command.chunky.group.help", "Type &e/chunky group ?&f for a list of sub-commands.");
    // Chunky Group Add
    public static final Message CMD_CHUNKY_GROUP_ADD_DESC = new Message("command.chunky.group.add.description", "Adds a player to a group.");
    public static final Message CMD_CHUNKY_GROUP_ADD_HELP = new Message("command.chunky.group.add.help",
            "Usage: &e/chunky group add <player> <group>",
            "Adds the specified player to the specified group.",
            "Example: &e/chunky group add dumptruckman friends");
    // Chunky Group Remove
    public static final Message CMD_CHUNKY_GROUP_RM_DESC = new Message("command.chunky.group.remove.description", "Removes a player from a group.");
    public static final Message CMD_CHUNKY_GROUP_RM_HELP = new Message("command.chunky.group.remove.help",
            "Usage: &e/chunky group remove <player> <group>",
            "Removes the specified player from the specified group.",
            "Example: &e/chunky group remove dumptruckman friends");
    // Chunky Admin
    public static final Message CMD_CHUNKY_ADMIN_HELP = new Message("command.chunky.admin.help", "Type &e/chunky admin ?&f for a list of sub-commands.");
    public static final Message CMD_CHUNKY_ADMIN_DESC = new Message("command.chunky.admin.description", "Contains admin-only commands.");
    // Chunky Admin Chunklimit
    public static final Message CMD_CHUNKY_ADMIN_CHUNKLIMIT_HELP = new Message("command.chunky.admin.chunk_limit.help",
            "Allows you to set a player's chunk claim limit.",
            "Usage: &e/chunky admin chunklimit <amount> <name>");
    public static final Message CMD_CHUNKY_ADMIN_CHUNKLIMIT_DESC = new Message("command.chunky.admin.chunk_limit.description", "Sets chunk claim limits.");
    // Chunky Admin Chunklimit
    public static final Message CMD_CHUNKY_ADMIN_ENABLEWORLD_HELP = new Message("command.chunky.admin.enable_world.help", "Allows you to enable chunky for your current world or a named world");
    public static final Message CMD_CHUNKY_ADMIN_ENABLEWORLD_DESC = new Message("command.chunky.admin.enable_world.description", "Enables Chunky per world.");
    // Chunky Admin Chunklimit
    public static final Message CMD_CHUNKY_ADMIN_DISABLEWORLD_HELP = new Message("command.chunky.admin.disable_world.help", "Allows you to disable chunky for your current world or a named world");
    public static final Message CMD_CHUNKY_ADMIN_DISABLEWORLD_DESC = new Message("command.chunky.admin.disable_world.description", "Disables Chunky per world.");
    // Addfriend
    public static final Message CMD_ADDFRIEND_HELP = new Message("command.addfriend.help",
            "An easy way to add someone to your friends list.",
            "Usage: &e/addfriend <name>");
    // Removefriend
    public static final Message CMD_RMFRIEND_HELP = new Message("command.rmfriend.help",
            "An easy way to remove someone from your friends list.",
            "Usage: &e/removefriend <name>");

    /**
     * Chunk
     */
    public static final Message UNREGISTERED_CHUNK_NAME = new Message("chunk.unowned_chunk_name", "(Wilderness)");
    public static final Message NO_SUCH_CHUNKS = new Message("chunk.no_such_chunks", "You own no chunks named: %1");
    public static final Message CHUNKS_NAMED = new Message("chunk.chunks_named", "chunks named: %1");
    public static final Message CHUNK_OWNED = new Message("chunk.owned", "This chunk is owned by: %1");
    public static final Message CHUNK_NOT_OWNED = new Message("chunk.not_owned", "This chunk is not owned!");
    public static final Message CHUNK_NAME_CHANGED = new Message("chunk.name_changed", "Changed name of chunk to: %1");
    public static final Message CHUNK_NONE_OWNED = new Message("chunk.none_owned", "You do not own any chunks!");
    public static final Message CHUNK_LIMIT_REACHED = new Message("chunk.limit", "You have claimed your maximum amount of chunks! (%1)");
    public static final Message CHUNK_CLAIMED = new Message("chunk.claimed", "You have claimed chunk at [%1, %2]!");
    public static final Message CHUNK_UNCLAIMED = new Message("chunk.unclaimed", "You have unclaimed chunk at [%1, %2]!");
    // Menu
    public static final Message CHUNK_MENU_TITLE = new Message("chunk.menu.title", "&8|----------&9%1&8----------|");
    public static final Message CHUNK_MENU_OWNER = new Message("chunk.menu.owner", "&aChunk Owner: &f%1");

    /**
     * Permission
     */
    public static final Message NO_PERMISSIONS_SET = new Message("permission.not_set", "NA");
    public static final Message NO_PERMISSIONS_GRANTED = new Message("permission.none", "NONE");
    public static final Message PERMISSIONS = new Message("permission.check", "Permissions on %1 are [%2] for %3");
    public static final Message PERMS_FOR_YOU = new Message("permission.for_you", "%1 set [%2] permissions for you on %3");
    public static final Message PERMISSIONS_STATUS = new Message("permission.status", "BUILD: %1 | DESTROY: %2 | ITEMUSE: %4 | SWITCH: %3");
    public static final Message PLAYER_PERMISSIONS = new Message("permission.player", "&aPlayer Permissions for %1: &f%2");
    public static final Message DEFAULT_PERMISSIONS = new Message("permission.default", "&aDefault Permissions for %1:");
    public static final Message YOUR_PERMISSIONS = new Message("permission.yours", "&aYour permissions for %1:");

    /**
     * Player
     */
    public static final Message NO_SUCH_PLAYER = new Message("player.no_such_player", "There is no player named: %1");
    // Menu
    public static final Message PLAYER_MENU_TITLE = new Message("player.menu.title",
            "&8|----------&9%1&8----------|",
            "&7First joined: &f%2",
            "&7Last seen: &f%3",
            "%4/%5 Chunks owned.");
    public static final Message PLAYER_MENU_OWNEDCHUNKS = new Message("player.menu.owned_chunks", "&aOwned Chunks:");
    // Mode
    public static final Message PLAYER_MODE_SET = new Message("player.mode.set",
            "Set player mode!",
            "Type &e/chunky player set mode clear&f to turn off");
    public static final Message PLAYER_MODE_CLEAR = new Message("player.mode.clear", "Cleared player mode!");

    /**
     * Group
     */
    public static final Message NO_SUCH_GROUP = new Message("group.no_such_group", "There is no group named: %1");
    public static final Message GROUP_ADD = new Message("group.add", "Added %1 to group: %2");
    public static final Message GROUP_REMOVE = new Message("group.remove", "Removed %1 from group: %2");

    /**
     * World
     */
    public static final Message NO_SUCH_WORLD = new Message("world.no_such_world", "There is no world named: %1");
    public static final Message ENABLED_WORLD = new Message("world.enabled_world", "Enabled Chunky for world: %1");
    public static final Message DISABLED_WORLD = new Message("world.disabled_world", "Disabled Chunky for world: %1");
}
