package com.dumptruckman.minecraft.chunky.json;

import com.dumptruckman.minecraft.chunky.persistence.Persistable;
import com.dumptruckman.minecraft.pluginbase.util.Logging;

public class JSONExceptionHandler {

    public static void handleJSONException(Persistable persistable, JSONException e) {
        Logging.severe("JSONException in " + persistable.getClassName() + ":" + persistable.getId());
        Logging.severe(e.getMessage());
    }
}
