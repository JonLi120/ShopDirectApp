package com.twobytwoshop.ShopDirect.core;

import android.content.Context;
import android.content.SharedPreferences;

import javax.annotation.Nonnull;

public class SharedPreferencesHelper {

    private static final String PREFERENCES_NAME = "shared_table";

    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;
    private static SharedPreferencesHelper helper;

    public static synchronized void init(Context context) {
        helper = new SharedPreferencesHelper();
        sp = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static SharedPreferencesHelper getInstance() {
        if (helper == null) {
            throw new RuntimeException("SharedPreferences helper need initial.");
        }
        return helper;
    }

    public void put(String key, Object object) {
        editor = sp.edit();

        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
        editor.apply();
    }

    public String getString(String key, @Nonnull String defValue) {
        return sp.getString(key, defValue);
    }

    public int getInt(String key, int defValue) {
        return sp.getInt(key, defValue);
    }

    public boolean getBoolean(String key, boolean deValue) {
        return sp.getBoolean(key, deValue);
    }

    public float getFloat(String key, float defValue) {
        return sp.getFloat(key, defValue);
    }

    public long getLong(String key, long defValue) {
        return sp.getLong(key, defValue);
    }

    public void removeOneKey(String key) {
        editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }

    public void clearAllData() {
        editor = sp.edit();
        editor.clear();
        editor.apply();
    }
}
