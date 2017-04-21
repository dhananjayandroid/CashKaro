package com.dhananjay.cashkaro_poc.ui.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

/**
 * Activity class for Splash extends {@link BaseActivity}
 *
 * @author Dhananjay Kumar
 */
public class SplashActivity extends BaseActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Log.d(TAG, "onCreate checkStartExistSession()");
        HomeActivity.start(this);
        finish();
    }

}