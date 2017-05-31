package com.uestc.naldo.psm;

import android.app.Application;
import android.content.Context;

/**
 * Created by Naldo on 2017/5/12.
 */

public class MyApplication extends Application {

    private static Context context;

    private final String TAG = "MyApplication";

    public static MyApplication instances;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        instances = this;
    }

    public static MyApplication getInstances(){
        return instances;
    }

    //获取全局Context
    public static Context getContext(){
        return context;
    }
}
