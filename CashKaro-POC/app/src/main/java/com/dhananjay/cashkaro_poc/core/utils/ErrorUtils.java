package com.dhananjay.cashkaro_poc.core.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

/**
 * Utility class for capturing and showing errors
 *
 * @author Dhananjay Kumar
 */
public class ErrorUtils {

    /**
     * Show error.
     *
     * @param context the context
     * @param e       the e
     */
    public static void showError(Context context, Exception e) {
        String errorMsg = !TextUtils.isEmpty(e.getMessage()) ? e.getMessage() : null;
        if (errorMsg != null) {
            Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show();
        }
        e.printStackTrace();
    }

    /**
     * Show error.
     *
     * @param context the context
     * @param error   the error
     */
    public static void showError(Context context, String error) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show();
    }

    /**
     * Gets error toast.
     *
     * @param context the context
     * @param error   the error
     * @return the error toast
     */
    public static Toast getErrorToast(Context context, String error) {
        return Toast.makeText(context, error, Toast.LENGTH_LONG);
    }

    /**
     * Log error.
     *
     * @param tag the tag
     * @param e   the e
     */
    public static void logError(String tag, Exception e) {
        String errorMsg = !TextUtils.isEmpty(e.getMessage()) ? e.getMessage() : "";
        Log.e(tag, errorMsg, e);
    }

    /**
     * Log error.
     *
     * @param tag the tag
     * @param msg the msg
     */
    public static void logError(String tag, String msg) {
        String errorMsg = !TextUtils.isEmpty(msg) ? msg : "";
        Log.e(tag, errorMsg);
    }

    /**
     * Log error.
     *
     * @param e the e
     */
    public static void logError(Exception e) {
        e.printStackTrace();
    }
}