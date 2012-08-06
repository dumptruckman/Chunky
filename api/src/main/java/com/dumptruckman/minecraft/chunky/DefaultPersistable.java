package com.dumptruckman.minecraft.chunky;

import com.dumptruckman.minecraft.chunky.json.JSONException;
import com.dumptruckman.minecraft.chunky.json.JSONObject;
import com.dumptruckman.minecraft.chunky.json.JSONTokener;
import com.dumptruckman.minecraft.chunky.persistence.Persistable;

import java.sql.Timestamp;

class DefaultPersistable implements Persistable {

    private static final int SEED = 42;
    private static final int fODD_PRIME_NUMBER = 37;

    private final int hashCode;

    private final String className;
    private final long id;

    private Timestamp lastUpdate;

    private JSONObject data = new JSONObject();

    DefaultPersistable(String className, long id, Timestamp lastUpdate) {
        this.className = className;
        this.id = id;
        this.lastUpdate = lastUpdate;
        this.hashCode = firstTerm(SEED) + (int)(id ^ (id >>> 32));
    }

    @Override
    public final long getId() {
        return id;
    }

    @Override
    public final String getClassName() {
        return className;
    }

    @Override
    public final Timestamp getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public final void setLastUpdate(Timestamp timestamp) {
        lastUpdate = timestamp;
    }

    @Override
    public final JSONObject getData() {
        return data;
    }

    @Override
    public final String jsonString() {
        return data.toString();
    }

    @Override
    public final void load(String jsonString) throws JSONException {
        JSONTokener x = new JSONTokener(jsonString);
        char c;
        String key;

        if (x.nextClean() != '{') {
            throw x.syntaxError("A JSONObject text must begin with '{'");
        }
        for (; ; ) {
            c = x.nextClean();
            switch (c) {
                case 0:
                    throw x.syntaxError("A JSONObject text must end with '}'");
                case '}':
                    return;
                default:
                    x.back();
                    key = x.nextValue().toString();
            }

            // The key is followed by ':'. We will also tolerate '=' or '=>'.

            c = x.nextClean();
            if (c == '=') {
                if (x.next() != '>') {
                    x.back();
                }
            } else if (c != ':') {
                throw x.syntaxError("Expected a ':' after a key");
            }
            getData().put(key, x.nextValue());

            // Pairs are separated by ','. We will also tolerate ';'.

            switch (x.nextClean()) {
                case ';':
                case ',':
                    if (x.nextClean() == '}') {
                        return;
                    }
                    x.back();
                    break;
                case '}':
                    return;
                default:
                    throw x.syntaxError("Expected a ',' or '}'");
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Persistable && ((Persistable)o).getId() == id;
    }

    @Override
    public final int hashCode() {
        return hashCode;
    }

    private static int firstTerm(final int aSeed) {
        return fODD_PRIME_NUMBER * aSeed;
    }
}
