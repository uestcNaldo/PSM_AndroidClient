package com.uestc.naldo.psm;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.uestc.naldo.psm.dao.DaoMaster;
import com.uestc.naldo.psm.dao.DaoSession;

/**
 * Created by Naldo on 2017/5/12.
 */

public class MyApplication extends Application {

    private static Context context;

    private final String TAG = "MyApplication";

    private DaoMaster.DevOpenHelper mDevOpenHelper;
    private SQLiteDatabase database;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    public static MyApplication instances;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        instances = this;
        setDatabase();
        Log.d(TAG, "onCreate: Database = "+database.toString());

    }

    public static MyApplication getInstances(){
        return instances;
    }

    //设置数据库
    private void setDatabase(){
        mDevOpenHelper = new DaoMaster.DevOpenHelper(this, "psm.db", null);
        database = mDevOpenHelper.getWritableDatabase();
        //数据库连接属于DaoMaster，多个Session指向同一个数据库
        mDaoMaster = new DaoMaster(database);
        mDaoSession = mDaoMaster.newSession();

    }
    public DaoSession getDaoSession(){
        return mDaoSession;
    }

    public SQLiteDatabase getDatabase(){
        return database;
    }

    //获取全局Context
    public static Context getContext(){
        return context;
    }
}
