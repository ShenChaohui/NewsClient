package com.geniuses.newsclient;

import android.app.Application;
import org.xutils.x;

/**
 * Created by Sch on 2018/1/25.
 */

public class NewsClientApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
