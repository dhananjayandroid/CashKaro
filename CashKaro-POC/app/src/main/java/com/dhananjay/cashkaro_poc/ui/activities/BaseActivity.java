package com.dhananjay.cashkaro_poc.ui.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NavUtils;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.dhananjay.cashkaro_poc.App;
import com.dhananjay.cashkaro_poc.R;
import com.dhananjay.cashkaro_poc.core.api.commands.Command;
import com.dhananjay.cashkaro_poc.core.helpers.PermissionHelper;
import com.dhananjay.cashkaro_poc.core.helpers.SharedHelper;
import com.dhananjay.cashkaro_poc.core.utils.ErrorUtils;
import com.dhananjay.cashkaro_poc.ui.fragments.dialogs.ProgressDialogFragment;
import com.dhananjay.cashkaro_poc.utils.ConnectivityUtils;
import com.dhananjay.cashkaro_poc.utils.ToastUtils;
import com.dhananjay.cashkaro_poc.utils.bridges.ActionBarBridge;
import com.dhananjay.cashkaro_poc.utils.bridges.ConnectionBridge;
import com.dhananjay.cashkaro_poc.utils.bridges.LoadingBridge;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Base for all activities class extends {@link AppCompatActivity}
 *
 * @author Dhananjay Kumar
 */
public class BaseActivity extends AppCompatActivity
        implements ActionBarBridge, ConnectionBridge, LoadingBridge, NavigationView.OnNavigationItemSelectedListener {
    /**
     * The Toolbar.
     */
    protected Toolbar toolbar;
    /**
     * The Activity title.
     */
    protected TextView activityTitle;
    /**
     * The Activity title logo.
     */
    protected ImageView activityTitleLogo;
    /**
     * The Action bar.
     */
    protected ActionBar actionBar;
    /**
     * The Title.
     */
    protected String title;
    /**
     * The App shared helper.
     */
    protected SharedHelper appSharedHelper;
    /**
     * The App.
     */
    protected App app;
    /**
     * The Permission helper.
     */
    protected PermissionHelper permissionHelper;
    /**
     * The firebase analytics.
     */
    protected FirebaseAnalytics mFirebaseAnalytics;
    /**
     * The Local broadcast manager.
     */
    protected LocalBroadcastManager localBroadcastManager;
    private BaseBroadcastReceiver broadcastReceiver;
    private Handler handler;
    private Map<String, Set<Command>> broadcastCommandMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFields();
    }

    /**
     * init Fields
     */
    private void initFields() {
        Log.d("BaseActivity", "initFields()");
        app = App.getInstance();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        appSharedHelper = App.getInstance().getAppSharedHelper();
        permissionHelper = new PermissionHelper(this);
        broadcastCommandMap = new HashMap<>();
        broadcastReceiver = new BaseBroadcastReceiver();
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
    }

    /**
     * Sets up action bar with up button.
     */
    protected void setUpActionBarWithUpButton() {
        initActionBar();
        setActionBarUpButtonEnabled(true);
        setActionBarTitle(title);
        activityTitleLogo.setVisibility(View.GONE);
    }

    /**
     * Sets up action bar with drawer.
     */
    protected void setUpActionBarWithDrawer() {
        initActionBar();
        setActionBarUpButtonEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        TextView tvUserName = (TextView) header.findViewById(R.id.tv_user_name);
        TextView tvUserEmail = (TextView) header.findViewById(R.id.tv_user_email);
        String userEmail = SharedHelper.getInstance().getUserEmail();
        if (userEmail != null && !TextUtils.isEmpty(userEmail)) {
            tvUserName.setText(SharedHelper.getInstance().getUserFullName());
            tvUserEmail.setText(SharedHelper.getInstance().getUserEmail());
        } else {
            tvUserName.setText("GUEST");
            tvUserEmail.setText("");
        }
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void initActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        activityTitle = (TextView) findViewById(R.id.tv_activity_title);
        activityTitleLogo = (ImageView) findViewById(R.id.iv_activity_title);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        actionBar = getSupportActionBar();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    @Override
    public void setActionBarTitle(String title) {
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    @Override
    public void setActionBarTitle(@StringRes int title) {
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    @Override
    public void setActionBarSubtitle(String subtitle) {
        if (actionBar != null) {
            actionBar.setSubtitle(subtitle);
        }
    }

    @Override
    public void setActionBarSubtitle(@StringRes int subtitle) {
        setActionBarSubtitle(getString(subtitle));
    }

    @Override
    public void setActionBarIcon(Drawable icon) {
        if (actionBar != null) {
            // In appcompat v21 there will be no icon if we don't add this display option
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setIcon(icon);
        }
    }

    @Override
    public void setActionBarIcon(@DrawableRes int icon) {
        Drawable drawable;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawable = getDrawable(icon);
        } else {
            drawable = getResources().getDrawable(icon);
        }

        setActionBarIcon(drawable);
    }

    @Override
    public void setActionBarUpButtonEnabled(boolean enabled) {
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(enabled);
            actionBar.setDisplayHomeAsUpEnabled(enabled);
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_account) {
            UserLoginActivity.start(this);
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_camera) {
            permissionHelper.checkAskCameraPermission();
        } else if (id == R.id.nav_location) {
            permissionHelper.checkAskLocationPermission();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean checkNetworkAvailableWithError() {
        if (!isNetworkAvailable()) {
            ToastUtils.longToast(R.string.error_fail_connection);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isNetworkAvailable() {
        return ConnectivityUtils.isNetworkAvailable(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                navigateToParent();
                break;
            case R.id.action_profile:
                UserLoginActivity.start(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Navigate to Parent Activity if exists else finish
     */
    private void navigateToParent() {
        Intent intent = NavUtils.getParentActivityIntent(this);
        if (intent == null) {
            finish();
        } else {
            NavUtils.navigateUpFromSameTask(this);
        }
    }

    @Override
    public synchronized void showProgress() {
        ProgressDialogFragment.show(getSupportFragmentManager());
    }

    @Override
    public synchronized void hideProgress() {
        ProgressDialogFragment.hide(getSupportFragmentManager());
    }

    @Override
    public void onRequestPermissionsResult(int pRequestCode, @NonNull String[] pPermissions, @NonNull int[] pGrantResults) {
        super.onRequestPermissionsResult(pRequestCode, pPermissions, pGrantResults);
        switch (pRequestCode) {
            case PermissionHelper.PERMISSIONS_REQUEST_CAMERA:
                permissionHelper.isCameraPermissionGranted();
                break;
            case PermissionHelper.PERMISSIONS_REQUEST_LOCATION:
                permissionHelper.isAllLocationPermissionGranted();
                break;
        }
    }

    /**
     * Record screen view.
     *
     * @param screenName the screen name
     * @param className  the class name
     */
    protected void recordScreenView(String screenName, String className) {
        mFirebaseAnalytics.setCurrentScreen(this, screenName, className);
    }

    /**
     * Update broadcast action list.
     */
    public void updateBroadcastActionList() {
        localBroadcastManager.unregisterReceiver(broadcastReceiver);
        IntentFilter intentFilter = new IntentFilter();
        for (String commandName : broadcastCommandMap.keySet()) {
            intentFilter.addAction(commandName);
        }
        localBroadcastManager.registerReceiver(broadcastReceiver, intentFilter);
    }

    /**
     * Base class for all other {@link BroadcastReceiver}
     */
    private class BaseBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, final Intent intent) {
            String action = intent.getAction();
            if (action != null) {
                Log.d("STEPS", "executing " + action);
                final Set<Command> commandSet = broadcastCommandMap.get(action);

                if (commandSet != null && !commandSet.isEmpty()) {
                    getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            for (Command command : commandSet) {
                                try {
                                    command.execute(intent.getExtras());
                                } catch (Exception e) {
                                    ErrorUtils.logError(e);
                                    hideProgress();
                                }
                            }
                        }
                    });
                }
            }
        }
    }

    /**
     * Get handler for broadcast actions
     * @return Handler
     */
    private Handler getHandler() {
        if (handler == null) {
            handler = new Handler();
        }
        return handler;
    }

    /**
     * Add action to broadcastCommandMap.
     *
     * @param action  the action
     * @param command the command
     */
    public void addAction(String action, Command command) {
        Set<Command> commandSet = broadcastCommandMap.get(action);
        if (commandSet == null) {
            commandSet = new HashSet<Command>();
            broadcastCommandMap.put(action, commandSet);
        }
        commandSet.add(command);
    }

    /**
     * Remove action to broadcastCommandMap.
     *
     * @param action the action
     */
    public void removeAction(String action) {
        broadcastCommandMap.remove(action);
    }
}
