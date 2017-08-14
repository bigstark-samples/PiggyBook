package com.bigstark.piggybook;

import android.app.Application;

/**
 * Created by bigstark on 2017. 7. 25..
 */

public class PiggyApplication extends Application {

    private static PiggyApplication instance;

    public static PiggyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
