package com.dumptruckman.minecraft.chunky.object;

import com.dumptruckman.minecraft.chunky.json.JSONObject;
import com.dumptruckman.minecraft.chunky.json.JSONTokener;

import java.sql.Timestamp;

class DefaultChunkyObject implements ChunkyObject {

    private static final int SEED = 42;
    private static final int fODD_PRIME_NUMBER = 37;

    private String className;
    private long id;

    private String name = "";
    private String description = "";
    private Timestamp lastUpdate;

    private JSONObject data = new JSONObject();

    DefaultChunkyObject(String className, long id, Timestamp lastUpdate) {
        this.className = className;
        this.id = id;
        this.lastUpdate = lastUpdate;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public void setLastUpdate(Timestamp timestamp) {
        lastUpdate = timestamp;
    }

    @Override
    public JSONObject getData() {
        return data;
    }

    @Override
    public String jsonString() {
        return data.toString();
    }

    @Override
    public void load(String jsonString) {
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
        return o instanceof ChunkyObject && ((ChunkyObject)o).getId() == id;
    }

    @Override
    public int hashCode() {
        return firstTerm(SEED)  + (int)( id ^ (id >>> 32) );
    }

    private static int firstTerm( int aSeed ){
        return fODD_PRIME_NUMBER * aSeed;
    }
}
