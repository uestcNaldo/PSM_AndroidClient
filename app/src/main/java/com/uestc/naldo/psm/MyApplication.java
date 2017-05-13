package com.uestc.naldo.psm;

import android.app.Application;
import android.content.Context;

/**
 * Created by Naldo on 2017/5/12.
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    //获取全局Context
    public static Context getContext(){
        return context;
    }
}
