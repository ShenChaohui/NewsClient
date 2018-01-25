package com.geniuses.newsclient.manager;

import com.google.gson.Gson;

/**
 * 单例Gson
 */
public class GsonManager {
    private GsonManager(){
    }
    private static class GsonHolder {
        private static final Gson mGson = new Gson();
    }
    public static Gson getGson(){
       return GsonHolder.mGson;
    }
}
