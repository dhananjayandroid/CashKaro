package com.dhananjay.cashkaro_poc;

import android.support.multidex.MultiDexApplication;

import com.dhananjay.cashkaro_poc.core.helpers.SharedHelper;

/**
 * {@link android.app.Application} class for our app
 *
 * @author Dhananjay Kumar
 */
public class App extends MultiDexApplication {

    private static App instance;
    private SharedHelper appSharedHelper;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initApplication();
    }


    private void initApplication() {
        instance = this;
    }


    public synchronized SharedHelper getAppSharedHelper() {
        return appSharedHelper == null
                ? appSharedHelper = new SharedHelper(this)
                : appSharedHelper;
    }


}