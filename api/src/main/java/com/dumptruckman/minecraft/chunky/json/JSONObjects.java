package com.dumptruckman.minecraft.chunky.json;

import com.sk89q.worldedit.Vector;

public class JSONObjects {

    private JSONObjects() {
        throw new AssertionError();
    }

    public static JSONObject valueOf(Vector vector) {
        JSONObject vec = new JSONObject();
        vec.putOpt("x", vector.getX());
        vec.putOpt("y", vector.getY());
        vec.putOpt("z", vector.getZ());
        return vec;
    }

    public static Vector toVector(JSONObject obj) throws JSONException {
        if (obj == null) {
            throw new JSONException("obj is null");
        }
        double x = obj.getDouble("x");
        double y = obj.getDouble("y");
        double z = obj.getDouble("z");
        return new Vector(x, y, z);
    }
}
