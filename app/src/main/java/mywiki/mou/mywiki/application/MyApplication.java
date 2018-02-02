package mywiki.mou.mywiki.application;

import android.app.Application;

import mywiki.mou.mywiki.database.MySQLiteInstance;

/**
 * Created by Mou on 2/2/2018.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MySQLiteInstance.open(this);
    }
}
