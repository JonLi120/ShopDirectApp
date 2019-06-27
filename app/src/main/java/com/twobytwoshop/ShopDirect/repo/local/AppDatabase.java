package com.twobytwoshop.ShopDirect.repo.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.twobytwoshop.ShopDirect.models.UserInfo;

@Database(entities = {UserInfo.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase appDatabase;
    private static final Object sLock = new Object();

    public abstract UserDao userDao();

    public static AppDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (appDatabase == null) {
                appDatabase = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "db").build();
            }
        }
        return appDatabase;
    }

}
