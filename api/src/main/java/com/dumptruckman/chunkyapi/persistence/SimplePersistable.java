package com.dumptruckman.chunkyapi.persistence;

import com.dumptruckman.chunkyapi.json.JSONException;
import com.dumptruckman.chunkyapi.json.JSONObject;
import com.dumptruckman.chunkyapi.json.JSONTokener;

import java.sql.Timestamp;

public class SimplePersistable implements Persistable {

    protected long id;
    protected String className;
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
}
