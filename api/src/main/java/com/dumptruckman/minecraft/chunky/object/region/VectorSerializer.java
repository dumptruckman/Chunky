package com.dumptruckman.minecraft.chunky.object.region;

import com.dumptruckman.minecraft.chunky.json.JSONException;
import com.dumptruckman.minecraft.chunky.json.JSONObject;
import com.sk89q.worldedit.Vector;

public class VectorSerializer {

    private VectorSerializer() {
        throw new AssertionError();
    }

    public static JSONObject toJSON(Vector vector) throws JSONException {
        JSONObject vec = new JSONObject();
        vec.putOpt("x", vector.getX());
        vec.putOpt("y", vector.getY());
        vec.putOpt("z", vector.getZ());
        return vec;
    }

    public static Vector fromJSON(JSONObject obj) throws JSONException {
        if (obj == null) {
            throw new JSONException("obj is null");
        }
        double x = obj.getDouble("x");
        double y = obj.getDouble("y");
        double z = obj.getDouble("z");
        return new Vector(x, y, z);
    }
}
