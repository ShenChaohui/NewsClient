package com.geniuses.newsclient.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Genius on 2017/7/3/0003.
 */

public class SharedPreferencesUtil {

    private static SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String name = "news_client_sp";

    public SharedPreferencesUtil(Context context) {
        sharedPreferences = context.getSharedPreferences(name, context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public boolean putString(String key, String value) {
        editor.putString(key, value);
        return editor.commit();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "0");
    }

    public String getString(String key, String defValue) {
        return sharedPreferences.getString(key, defValue);
    }


}