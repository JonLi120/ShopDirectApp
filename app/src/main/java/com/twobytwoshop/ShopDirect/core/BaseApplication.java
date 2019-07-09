package com.twobytwoshop.ShopDirect.core;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;
import com.twobytwoshop.ShopDirect.BuildConfig;
import com.twobytwoshop.ShopDirect.utils.SharedPreferencesUtil;

import androidx.multidex.MultiDex;

import java.util.UUID;

import io.fabric.sdk.android.Fabric;

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

        SharedPreferencesUtil.getInstance().setDeviceId(getMyUUID());

        Fabric.with(this, new Crashlytics());
    }

    private String getMyUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
