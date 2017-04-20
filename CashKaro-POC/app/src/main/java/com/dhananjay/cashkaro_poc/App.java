package com.dhananjay.cashkaro_poc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.multidex.MultiDexApplication;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;



public class App extends MultiDexApplication {

    private static App instance;
//    private SharedHelper appSharedHelper;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initApplication();
//        Stetho.initializeWithDefaults(this);
    }


    private void initApplication() {
        instance = this;
    }


//    public synchronized SharedHelper getAppSharedHelper() {
//        return appSharedHelper == null
//                ? appSharedHelper = new SharedHelper(this)
//                : appSharedHelper;
//    }


}