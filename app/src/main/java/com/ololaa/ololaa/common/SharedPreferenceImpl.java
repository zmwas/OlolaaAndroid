package com.ololaa.ololaa.common;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceImpl implements SharedPrefsWrapper {
    private SharedPreferences sharedPreferences;

    private final Context context;
    private static final String PREFS = "Ololaa_prefs";

    /**
     * @param context - context
     */
    public SharedPreferenceImpl(Context context) {
        this.context = context;
    }

    /**
     * @param key   - key
     * @param value - value
     */
    @Override
    public void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS, 0).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * @param key - key
     * @return boolean
     */
    @Override
    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    /**
     * @param key   - key
     * @param value - value
     */
    @Override
    public void putString(String key, String value) {

        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS, 0).edit();
        editor.putString(key, value);
        editor.apply();

    }

    /**
     * @param key - key
     * @return string
     */
    @Override
    public String getString(String key) {

        sharedPreferences = context.getSharedPreferences(PREFS, 0);


        return sharedPreferences.getString(key, null);
    }

    @Override
    public void putLong(String key, Long value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS, 0).edit();
        editor.putLong(key, value);
        editor.apply();

    }

    @Override
    public Long getLong(String key) {
        sharedPreferences = context.getSharedPreferences(PREFS, 0);

        return sharedPreferences.getLong(key, -1);
    }

    /**
     * Clear shared preferences on log out.
     */
    public void clear() {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS, 0).edit();
        editor.clear();
        editor.apply();
    }
}
