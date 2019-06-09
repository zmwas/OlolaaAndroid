package com.ololaa.ololaa.common;

public interface SharedPrefsWrapper {

    /**
     *
     * @param key - key
     * @param value - value
     */
    void putBoolean(String key, boolean value);

    /**
     *
     * @param key - key
     * @return boolean
     */
    boolean getBoolean(String key);

    /**
     *
     * @param key - key
     * @param value - value
     */
    void putString(String key, String value);

    /**
     *
     * @param key - key
     * @return string
     */
    String getString(String key);

    void putLong(String key, Long value);

    Long getLong(String key);

    void clear();
}
