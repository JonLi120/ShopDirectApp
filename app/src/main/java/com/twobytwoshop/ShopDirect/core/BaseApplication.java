package com.twobytwoshop.ShopDirect.core;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.twobytwoshop.ShopDirect.BuildConfig;

import androidx.multidex.MultiDex;

public class BaseApplication extends Application {

    private static BaseApplication baseApplication;
    private static SharedPreferencesHelper helper;

    public static BaseApplication getInstance() {
        return baseApplication;
    }

    public SharedPreferencesHelper getSharedPreferencesHelper() {
        if (helper == null) {
            helper = SharedPreferencesHelper.getInstance();
        }
        return helper;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;

        SharedPreferencesHelper.init(this);

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }
}
