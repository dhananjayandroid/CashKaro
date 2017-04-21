package com.dhananjay.cashkaro_poc.core.helpers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Helper class for Saving and retrieving data form {@link SharedPreferences}
 *
 * @author Dhananjay Kumar
 */
public class SharedHelper {


    /**
     * The type Constants.
     */
    public class Constants {

        /**
         * The constant NAME.
         */
        public static final String NAME = "CashKaro-POC";

        /**
         * The constant LOGIN_TYPE.
         */
        public static final String LOGIN_TYPE = "login_type";
        /**
         * The constant FB_TOKEN.
         */
        public static final String FB_TOKEN = "fb_token";

        /**
         * The constant USER_ID.
         */
        public static final String USER_ID = "user_id";
        /**
         * The constant USER_EMAIL.
         */
        public static final String USER_EMAIL = "user_email";
        /**
         * The constant USER_PASSWORD.
         */
        public static final String USER_PASSWORD = "user_password";
        /**
         * The constant USER_FULL_NAME.
         */
        public static final String USER_FULL_NAME = "full_name";
        /**
         * The constant USER_FB_ID.
         */
        public static final String USER_FB_ID = "facebook_id";

        /**
         * The constant PERMISSIONS_SAVE_FILE_WAS_REQUESTED.
         */
        public static final String PERMISSIONS_SAVE_FILE_WAS_REQUESTED = "permission_save_file_was_requested";
    }

    /**
     * The Shared preferences.
     */
    protected final SharedPreferences sharedPreferences;
    /**
     * The Shared preferences editor.
     */
    protected final SharedPreferences.Editor sharedPreferencesEditor;

    private static SharedHelper instance;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static SharedHelper getInstance() {
        if (instance == null) {
            throw new NullPointerException("SharedHelper was not initialized!");
        }
        return instance;
    }

    /**
     * Instantiates a new Shared helper.
     *
     * @param context the context
     */
    public SharedHelper(Context context) {
        instance = this;
        sharedPreferences = context.getSharedPreferences(Constants.NAME, Context.MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();
    }

    /**
     * Delete.
     *
     * @param key the key
     */
    protected void delete(String key) {
        if (sharedPreferences.contains(key)) {
            sharedPreferencesEditor.remove(key).commit();
        }
    }

    /**
     * Save pref.
     *
     * @param key   the key
     * @param value the value
     */
    protected void savePref(String key, Object value) {
        delete(key);

        if (value instanceof Boolean) {
            sharedPreferencesEditor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Integer) {
            sharedPreferencesEditor.putInt(key, (Integer) value);
        } else if (value instanceof Float) {
            sharedPreferencesEditor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            sharedPreferencesEditor.putLong(key, (Long) value);
        } else if (value instanceof String) {
            sharedPreferencesEditor.putString(key, (String) value);
        } else if (value instanceof Enum) {
            sharedPreferencesEditor.putString(key, value.toString());
        } else if (value != null) {
            throw new RuntimeException("Attempting to save non-primitive preference");
        }

        sharedPreferencesEditor.commit();
    }

    /**
     * Gets pref.
     *
     * @param <T> the type parameter
     * @param key the key
     * @return the pref
     */
    protected <T> T getPref(String key) {
        return (T) sharedPreferences.getAll().get(key);
    }

    /**
     * Gets pref.
     *
     * @param <T>      the type parameter
     * @param key      the key
     * @param defValue the def value
     * @return the pref
     */
    protected <T> T getPref(String key, T defValue) {
        T returnValue = (T) sharedPreferences.getAll().get(key);
        return returnValue == null ? defValue : returnValue;
    }

    /**
     * Clear all.
     */
    public void clearAll() {
        sharedPreferencesEditor.clear();
    }


    /**
     * Gets fb token.
     *
     * @return the fb token
     */
    public String getFBToken() {
        return getPref(Constants.FB_TOKEN, null);
    }

    /**
     * Save fb token.
     *
     * @param token the token
     */
    public void saveFBToken(String token) {
        savePref(Constants.FB_TOKEN, token);
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUserId() {
        return getPref(Constants.USER_ID, 0);
    }

    /**
     * Save user id.
     *
     * @param id the id
     */
    public void saveUserId(int id) {
        savePref(Constants.USER_ID, id);
    }

    /**
     * Gets user email.
     *
     * @return the user email
     */
    public String getUserEmail() {
        return getPref(Constants.USER_EMAIL, null);
    }

    /**
     * Save user email.
     *
     * @param email the email
     */
    public void saveUserEmail(String email) {
        savePref(Constants.USER_EMAIL, email);
    }

    /**
     * Gets user password.
     *
     * @return the user password
     */
    public String getUserPassword() {
        return getPref(Constants.USER_PASSWORD, null);
    }

    /**
     * Save user password.
     *
     * @param password the password
     */
    public void saveUserPassword(String password) {
        savePref(Constants.USER_PASSWORD, password);
    }

    /**
     * Gets user full name.
     *
     * @return the user full name
     */
    public String getUserFullName() {
        return getPref(Constants.USER_FULL_NAME, null);
    }

    /**
     * Save user full name.
     *
     * @param fullName the full name
     */
    public void saveUserFullName(String fullName) {
        savePref(Constants.USER_FULL_NAME, fullName);
    }

    /**
     * Save fb id.
     *
     * @param facebookId the facebook id
     */
    public void saveFBId(String facebookId) {
        savePref(Constants.USER_FB_ID, facebookId);
    }

    /**
     * Gets fb id.
     *
     * @return the fb id
     */
    public String getFBId() {
        return getPref(Constants.USER_FB_ID);
    }


    /**
     * Is permissions save file was requested boolean.
     *
     * @return the boolean
     */
    public boolean isPermissionsSaveFileWasRequested() {
        return getPref(Constants.PERMISSIONS_SAVE_FILE_WAS_REQUESTED, false);
    }

    /**
     * Save permissions save file was requested.
     *
     * @param requested the requested
     */
    public void savePermissionsSaveFileWasRequested(boolean requested) {
        savePref(Constants.PERMISSIONS_SAVE_FILE_WAS_REQUESTED, requested);
    }

    /**
     * Clear user data.
     */
    public void clearUserData() {
        saveUserId(0);
        saveUserEmail(null);
        saveUserPassword(null);
        saveUserFullName(null);
        saveFBId(null);
    }

}