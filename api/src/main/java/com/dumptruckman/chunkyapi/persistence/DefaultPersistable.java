package com.dumptruckman.chunkyapi.persistence;

import com.dumptruckman.chunkyapi.json.JSONException;
import com.dumptruckman.chunkyapi.json.JSONObject;
import com.dumptruckman.chunkyapi.json.JSONTokener;

import java.sql.Timestamp;

public class DefaultPersistable implements Persistable {

    private static final int SEED = 42;
    private static final int fODD_PRIME_NUMBER = 37;

    protected long id;
    protected String className;
    protected String name = "";
    protected String description = "";
    private Timestamp timestamp;

    private JSONObject data = new JSONObject();

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getClassName() {
        return className;
    }

    /**
     * @return The name of this object.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this object to name.
     *
     * @param name New name for object.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return A description of this object, may be blank.
     */
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
        return timestamp;
    }

    @Override
    public void setLastUpdate(Timestamp timestamp) {
        this.timestamp = timestamp;
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
    public void load(String json) throws JSONException {
        JSONTokener x = new JSONTokener(json);
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
    public int hashCode() {
        return firstTerm(SEED)  + (int)( id ^ (id >>> 32) );
    }

    private static int firstTerm( int aSeed ){
        return fODD_PRIME_NUMBER * aSeed;
    }
}
