package com.dhananjay.cashkaro_poc.core.helpers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.dhananjay.cashkaro_poc.utils.AppConstants;
import com.dhananjay.cashkaro_poc.utils.listeners.FbResultListener;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Helper class for Facebook login and actions
 *
 * @author Dhananjay Kumar
 */
public class FacebookHelper {

    private static final String PERMISSION_EMAIL = "email";
    private static final String PERMISSION_USER_FRIENDS = "user_friends";

    private Activity activity;
    private FBAccessTokenTracker fbAccessTokenTracker;
    private CallbackManager fbCallbackManager;
    private LoginManager fbLoginManager;

    /**
     * Instantiates a new Facebook helper.
     *
     * @param activity the activity
     */
    public FacebookHelper(Activity activity) {
        this.activity = activity;
        initFacebook();
    }

    /**
     * Logout from Facebook.
     */
    public void logout() {
        LoginManager.getInstance().logOut();
        SharedHelper.getInstance().clearUserData();
    }

    private void initFacebook() {
        FacebookSdk.sdkInitialize(activity.getApplicationContext());

        fbCallbackManager = CallbackManager.Factory.create();

        fbLoginManager = LoginManager.getInstance();

        this.fbAccessTokenTracker = new FBAccessTokenTracker();
    }

    /**
     * On activity result.
     *
     * @param requestCode the request code
     * @param resultCode  the result code
     * @param data        the data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        fbCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Login to Facebook.
     *
     * @param facebookLoginCallback the facebook login callback
     */
    public void login(FacebookCallback<LoginResult> facebookLoginCallback) {
        fbLoginManager.registerCallback(fbCallbackManager, facebookLoginCallback);
        fbLoginManager.logInWithReadPermissions(activity, generatePermissionsList());
    }

    /**
     * Generate permissions list.
     *
     * @return the list
     */
    public List<String> generatePermissionsList() {
        List<String> permissionsList = new ArrayList<String>();
        permissionsList.add(PERMISSION_EMAIL);
        permissionsList.add(PERMISSION_USER_FRIENDS);
        return permissionsList;
    }

    /**
     * On activity start.
     */
    public void onActivityStart() {
        fbAccessTokenTracker.startTracking();
    }

    /**
     * On activity stop.
     */
    public void onActivityStop() {
        fbAccessTokenTracker.stopTracking();
    }

    /**
     * Is session opened boolean.
     *
     * @return the boolean
     */
    public boolean isSessionOpened() {
        return SharedHelper.getInstance().getFBToken() != null && AccessToken.getCurrentAccessToken() != null;
    }

    private class FBAccessTokenTracker extends AccessTokenTracker {

        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if (currentAccessToken != null) {
                SharedHelper.getInstance().saveFBToken(currentAccessToken.getToken());
            } else {
                SharedHelper.getInstance().saveFBToken(null);
            }
        }
    }

    /**
     * Gets details of the logged in user.
     *
     * @param loginResult      the login result
     * @param fbResultListener the fb result listener
     * @return the details
     */
    public String getDetails(LoginResult loginResult, final FbResultListener fbResultListener) {
        String email = "";
        GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            Bundle bundle = new Bundle();
                            bundle.putString(AppConstants.EXTRA_NAME, object.getString("name"));
                            bundle.putString(AppConstants.EXTRA_EMAIL, object.getString("email"));
                            fbResultListener.onLoginResult(bundle);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender,birthday");
        request.setParameters(parameters);
        request.executeAsync();
        return email;
    }
}