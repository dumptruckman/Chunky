package com.dumptruckman.minecraft.chunky;

import com.dumptruckman.minecraft.chunky.json.JSONArray;
import com.dumptruckman.minecraft.chunky.json.JSONException;
import com.dumptruckman.minecraft.chunky.json.JSONObject;
import com.dumptruckman.minecraft.chunky.json.JSONString;
import com.dumptruckman.minecraft.chunky.json.JSONTokener;
import com.dumptruckman.minecraft.chunky.persistence.JSONField;
import com.dumptruckman.minecraft.chunky.persistence.JSONPath;
import com.dumptruckman.minecraft.chunky.persistence.JSONSerialized;
import com.dumptruckman.minecraft.chunky.persistence.NotNull;
import com.dumptruckman.minecraft.chunky.persistence.Persistable;
import com.dumptruckman.minecraft.chunky.persistence.Table;
import com.dumptruckman.minecraft.pluginbase.util.Logging;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Table("ChunkyObject")
class DefaultPersistable implements Persistable {

    private static final int SEED = 42;
    private static final int fODD_PRIME_NUMBER = 37;

    private final int hashCode;

    private final String className;
    private final long id;

    private Timestamp lastUpdate;

    private final JSONObject data;

    private final Map<Class, Field[]> jsonFields = new HashMap<Class, Field[]>();
    private final Map<Class, Map<Field, Method>> jsonSerializers = new HashMap<Class, Map<Field, Method>>();
    private final Map<Class, Map<Field, Method>> jsonDeserializers = new HashMap<Class, Map<Field, Method>>();

    DefaultPersistable(String className, long id, Timestamp lastUpdate, String jsonData, boolean newObject) throws JSONException, IllegalArgumentException {
        this.className = className;
        this.id = id;
        this.lastUpdate = lastUpdate;
        this.data = load(jsonData);
        this.hashCode = firstTerm(SEED) + (int)(id ^ (id >>> 32));


        initFields();

        populateFields(newObject);
    }

    private void initFields() {
        if (!jsonFields.containsKey(getClass())) {
            Field[] declaredFields = getClass().getDeclaredFields();
            List<Field> jsonFields = new ArrayList<Field>(declaredFields.length);
            List<Field> jsonSerializedFields = new ArrayList<Field>(declaredFields.length);
            for (Field field : getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(JSONField.class)) {
                    if (field.isAnnotationPresent(JSONSerialized.class)) {
                        jsonSerializedFields.add(field);
                    } else {
                        Class type = field.getType();
                        if (!JSONObject.class.isAssignableFrom(type)
                                && !JSONArray.class.isAssignableFrom(type)
                                && !JSONString.class.isAssignableFrom(type)
                                && !Byte.class.isAssignableFrom(type)
                                && !Character.class.isAssignableFrom(type)
                                && !Short.class.isAssignableFrom(type)
                                && !Integer.class.isAssignableFrom(type)
                                && !Long.class.isAssignableFrom(type)
                                && !Boolean.class.isAssignableFrom(type)
                                && !Float.class.isAssignableFrom(type)
                                && !Double.class.isAssignableFrom(type)
                                && !byte.class.isAssignableFrom(type)
                                && !char.class.isAssignableFrom(type)
                                && !short.class.isAssignableFrom(type)
                                && !int.class.isAssignableFrom(type)
                                && !long.class.isAssignableFrom(type)
                                && !boolean.class.isAssignableFrom(type)
                                && !float.class.isAssignableFrom(type)
                                && !double.class.isAssignableFrom(type)
                                && !String.class.isAssignableFrom(type)) {
                            throw new IllegalArgumentException(getClass() + "#" + field.getName() + " cannot be automatically serialized with JSON.  Requires JSONSerialized annotation.");
                        }
                    }
                    jsonFields.add(field);
                }
            }
            this.jsonFields.put(getClass(), jsonFields.toArray(new Field[jsonFields.size()]));
            Map<Field, Method> jsonSerializers = new HashMap<Field, Method>(jsonSerializedFields.size());
            Map<Field, Method> jsonDeserializers = new HashMap<Field, Method>(jsonSerializedFields.size());
            for (Field field : jsonSerializedFields) {
                JSONSerialized jsonSerialized = field.getAnnotation(JSONSerialized.class);
                jsonSerializers.put(field, getJsonSerializer(jsonSerialized.value(), field.getType()));
                jsonDeserializers.put(field, getJsonDeserializer(jsonSerialized.value(), field.getType()));
            }
            this.jsonSerializers.put(getClass(), jsonSerializers);
            this.jsonDeserializers.put(getClass(), jsonDeserializers);
        }
    }

    private void populateFields(boolean newObject) throws JSONException {
        for (Field field : jsonFields.get(getClass())) {
            JSONObject data = getData();
            if (getClass().isAnnotationPresent(JSONPath.class)) {
                data = getPathData(getClass().getAnnotation(JSONPath.class), data);
            }
            String key = field.getAnnotation(JSONField.class).value();
            if (key.isEmpty()) {
                key = field.getName();
            }
            Object value = data.opt(key);
            if (value == null) {
                if (!newObject && field.isAnnotationPresent(NotNull.class)) {
                    throw new JSONException("JSON data is missing for " + getClass() + "#" + field.getName());
                }
                continue;
            }
            if (getClass().isAnnotationPresent(JSONSerialized.class)) {
                Method deserializeMethod = jsonDeserializers.get(getClass()).get(field);
                deserializeMethod.setAccessible(true);
                try {
                    value = deserializeMethod.invoke(value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            if (field.getType().isAssignableFrom(value.getClass())) {
                boolean access = field.isAccessible();
                field.setAccessible(true);
                try {
                    field.set(this, value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                field.setAccessible(access);
            } else {
                Logging.warning(value + " is an invalid value for " + getClass() + "#" + field.getName());
            }
        }
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
        JSONObject data = this.data;
        if (getClass().isAnnotationPresent(JSONPath.class)) {
            data = getPathData(getClass().getAnnotation(JSONPath.class), data);
        }
        return data;
    }

    @Override
    public final String jsonString() {
        return data.toString();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Persistable && ((Persistable)o).getId() == id;
    }

    @Override
    public final int hashCode() {
        return hashCode;
    }

    @Override
    public String getTable() {
        return getClass().getAnnotation(Table.class).value();
    }

    @Override
    public void serialize() {
        for (Field field : jsonFields.get(getClass())) {
            JSONObject data = getData();
            if (getClass().isAnnotationPresent(JSONPath.class)) {
                data = getPathData(getClass().getAnnotation(JSONPath.class), data);
            }
            final boolean access = field.isAccessible();
            Object value = null;
            field.setAccessible(true);
            try {
                value = field.get(this);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            field.setAccessible(access);
            String key = field.getAnnotation(JSONField.class).value();
            if (key.isEmpty()) {
                key = field.getName();
            }
            if (value == null) {
                data.remove(key);
            } else {
                if (getClass().isAnnotationPresent(JSONSerialized.class)) {
                    Method serializeMethod = jsonSerializers.get(getClass()).get(field);
                    serializeMethod.setAccessible(true);
                    try {
                        value = serializeMethod.invoke(value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
                data.putOpt(key, value);
            }
        }
    }

    private static int firstTerm(final int aSeed) {
        return fODD_PRIME_NUMBER * aSeed;
    }

    private static JSONObject load(String jsonString) throws JSONException {
        JSONObject data = new JSONObject();
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
                    return data;
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
            data.put(key, x.nextValue());

            // Pairs are separated by ','. We will also tolerate ';'.

            switch (x.nextClean()) {
                case ';':
                case ',':
                    if (x.nextClean() == '}') {
                        return data;
                    }
                    x.back();
                    break;
                case '}':
                    return data;
                default:
                    throw x.syntaxError("Expected a ',' or '}'");
            }
        }
    }

    private static Method getJsonSerializer(Class serializer, Class clazz) throws IllegalArgumentException {
        try {
            Method serializeMethod = serializer.getDeclaredMethod("toJSON", clazz);
            if (!serializeMethod.getReturnType().equals(JSONObject.class)) {
                throw new IllegalArgumentException("serialize method must be: public static JSONObject toJSON(" + clazz.getName() + ")");
            }
            return serializeMethod;
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(clazz + " must haven methods: public static JSONObject toJSON(" + clazz.getName() + ") and public static " + clazz.getName() + " fromJSON(JSONObject)");
        }
    }

    private static Method getJsonDeserializer(Class serializer, Class clazz) throws IllegalArgumentException {
        try {
            Method deserializeMethod = serializer.getDeclaredMethod("fromJSON", JSONObject.class);
            if (!deserializeMethod.getReturnType().equals(clazz)) {
                throw new IllegalArgumentException("deserialize method must be: public static " + clazz.getName() + " fromJSON(JSONObject)");
            }
            return deserializeMethod;
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(clazz + " must haven methods: public static JSONObject toJSON(" + clazz.getName() + ") and public static " + clazz.getName() + " fromJSON(JSONObject)");
        }
    }

    private static JSONObject getPathData(final JSONPath jsonPath, JSONObject data) {
        for (String currentPath : jsonPath.value()) {
            JSONObject d = data.optJSONObject(currentPath);
            if (d == null) {
                d = new JSONObject();
                data.put(currentPath, d);
            }
            data = d;
        }
        return data;
    }
}
