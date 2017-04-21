package com.dhananjay.cashkaro_poc.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dhananjay.cashkaro_poc.R;
import com.dhananjay.cashkaro_poc.core.helpers.FacebookHelper;
import com.dhananjay.cashkaro_poc.core.helpers.SharedHelper;
import com.dhananjay.cashkaro_poc.utils.AppConstants;
import com.dhananjay.cashkaro_poc.utils.listeners.FbResultListener;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;

/**
 * Activity class for User Login extends {@link BaseActivity}
 *
 * @author Dhananjay Kumar
 */
public class UserLoginActivity extends BaseActivity implements View.OnClickListener, FbResultListener {

    private ImageView ivFbLogin;
    private TextView tvFbLogin;
    private Button btnLogOut;
    private FacebookHelper facebookHelper;
    private String userEmail;
    private static String TAG = UserLoginActivity.class.getSimpleName();

    public static void start(Context context) {
        Intent intent = new Intent(context, UserLoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        initViews();
        initFields();
        setUpActionBarWithUpButton();
        initListeners();
    }

    /**
     * Initiate all listeners
     */
    private void initListeners() {
        ivFbLogin.setOnClickListener(this);
        btnLogOut.setOnClickListener(this);
    }

    /**
     * initiate all view elements
     */
    private void initViews() {
        ivFbLogin = (ImageView) findViewById(R.id.iv_fb_login);
        tvFbLogin = (TextView) findViewById(R.id.tv_fb_login);
        btnLogOut = (Button) findViewById(R.id.btn_log_out);
    }

    /**
     * init Fields
     */
    private void initFields() {
        facebookHelper = new FacebookHelper(this);
        title = "Account";
        userEmail = SharedHelper.getInstance().getUserEmail();
        setLoggedInUser(userEmail);
    }

    /**
     * Set logged in user details
     *
     * @param email email address
     */
    private void setLoggedInUser(String email) {
        if (email != null && !TextUtils.isEmpty(email)) {
            tvFbLogin.setText(String.format(getResources().getString(R.string.user_logged_in), email));
            ivFbLogin.setVisibility(View.GONE);
            tvFbLogin.setVisibility(View.VISIBLE);
            btnLogOut.setVisibility(View.VISIBLE);
        } else {
            ivFbLogin.setVisibility(View.VISIBLE);
            tvFbLogin.setVisibility(View.GONE);
            btnLogOut.setVisibility(View.GONE);
        }
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_fb_login:
                facebookHelper.login(new FacebookLoginCallback());
                break;
            case R.id.btn_log_out:
                recordLogOut(userEmail);
                facebookHelper.logout();
                setLoggedInUser(null);
                Intent intent = new Intent(AppConstants.REFRESH_USER_DETAILS);
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
                break;
        }
    }

    @Override
    public void onLoginResult(Bundle result) {
        hideProgress();
        userEmail = result.getString(AppConstants.EXTRA_EMAIL);
        SharedHelper.getInstance().saveUserEmail(userEmail);
        SharedHelper.getInstance().saveUserFullName(result.getString(AppConstants.EXTRA_NAME));
        setLoggedInUser(userEmail);
        recordLogin(userEmail);
        Intent intent = new Intent(AppConstants.REFRESH_USER_DETAILS);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    /**
     * Records facebook login
     *
     * @param email
     */
    private void recordLogin(String email) {
        Bundle params = new Bundle();
        params.putString("user_email", email);
        mFirebaseAnalytics.logEvent("login", params);
    }

    /**
     * Records facebook logout
     *
     * @param email
     */
    private void recordLogOut(String email) {
        Bundle params = new Bundle();
        params.putString("user_email", email);
        mFirebaseAnalytics.logEvent("logout", params);
    }

    /**
     * Class for Facebook Login Callback
     */
    private class FacebookLoginCallback implements FacebookCallback<LoginResult> {

        @Override
        public void onSuccess(LoginResult loginResult) {
            Log.d(TAG, "+++ FacebookCallback call onSuccess from UserLoginActivity +++");
            showProgress();
            facebookHelper.getDetails(loginResult, UserLoginActivity.this);
        }

        @Override
        public void onCancel() {
            Log.d(TAG, "+++ FacebookCallback call onCancel from UserLoginActivity +++");
            hideProgress();
        }

        @Override
        public void onError(FacebookException error) {
            Log.d(TAG, "+++ FacebookCallback call onCancel UserLoginActivity +++");
            hideProgress();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        facebookHelper.onActivityStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        facebookHelper.onActivityStop();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        facebookHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        recordScreenView("User-Account", OfferDealActivity.class.getSimpleName());
    }

}
