package org.json;

/**
 * The <code>JSONString</code> interface allows a <code>jsonString()</code>
 * method so that a class can change the behavior of
 * <code>JSONObject.toLongString()</code>, <code>JSONArray.toLongString()</code>,
 * and <code>JSONWriter.value(</code>Object<code>)</code>. The
 * <code>jsonString</code> method will be used instead of the default behavior
 * of using the Object's <code>toLongString()</code> method and quoting the result.
 */
public interface JSONString {
    /**
     * The <code>jsonString</code> method allows a class to produce its own JSON
     * serialization.
     *
     * @return A strictly syntactically correct JSON text.
     */
    public String toJSONString();
}
