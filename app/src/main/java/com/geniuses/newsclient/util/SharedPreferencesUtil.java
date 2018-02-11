package com.geniuses.newsclient.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

/**
 * Created by Genius on 2017/7/3/0003.
 */

public class SharedPreferencesUtil {

    public static void saveString(Context mActivity, String key, String value) {
        SharedPreferences sp = mActivity.getSharedPreferences(GlobalValue.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

    public static String getString(Context mActivity, String key, String defValue) {
        SharedPreferences sp = mActivity.getSharedPreferences(GlobalValue.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    public static void saveObject(Context mActivity, String key, Object value) {
        SharedPreferences sp = mActivity.getSharedPreferences(GlobalValue.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonString = gson.toJson(value);
        sp.edit().putString(key, jsonString).commit();
    }

    @Nullable
    public static Object getObject(Context mActivity, String key, Class cls) {
        SharedPreferences sp = mActivity.getSharedPreferences(GlobalValue.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonString = sp.getString(key,"");
        if (CommonUtils.isEmpty(jsonString))
            return null;
        Object object = gson.fromJson(jsonString,cls);
        return object;
    }


    public static void saveBoolean(Activity mActivity, String key, boolean value) {
        SharedPreferences sp = mActivity.getSharedPreferences(GlobalValue.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(key, value);
        edit.commit();
    }

    public static boolean getBoolean(Activity mActivity, String key, boolean defValue) {
        SharedPreferences sp = mActivity.getSharedPreferences(GlobalValue.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }


}