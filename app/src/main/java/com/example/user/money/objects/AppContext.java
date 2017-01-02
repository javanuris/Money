package com.example.user.money.objects;

import android.app.Application;

import com.example.user.money.database.DbAdapter;

/**
 * Created by User on 02.01.2017.
 */

public class AppContext extends Application{
private static DbAdapter dbAdapter;

    @Override
    public void onCreate() {
        super.onCreate();
    dbAdapter = new DbAdapter(this);
    }

    public static DbAdapter getDbAdapter(){
        return dbAdapter;
    }
}
