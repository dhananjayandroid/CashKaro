package com.dhananjay.cashkaro_poc.core.helpers;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import com.dhananjay.cashkaro_poc.R;
import com.dhananjay.cashkaro_poc.utils.ToastUtils;


/**
 * Helper class for gaining the app permissions
 *
 * @author Dhananjay Kumar
 */
public class PermissionHelper {
    private Activity activity;
    /**
     * The constant PERMISSIONS_REQUEST_CAMERA.
     */
    public static final int PERMISSIONS_REQUEST_CAMERA = 111;
    /**
     * The constant PERMISSIONS_REQUEST_LOCATION.
     */
    public static final int PERMISSIONS_REQUEST_LOCATION = 222;

    /**
     * Instantiates a new Permission helper.
     *
     * @param activity the activity
     */
    public PermissionHelper(Activity activity) {
        this.activity = activity;
    }

    /**
     * Check and ask location permission.
     */
    public void checkAskLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {

                String[] lPermissions = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
                activity.requestPermissions(lPermissions, PERMISSIONS_REQUEST_LOCATION);
                return;
            } else if (activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                String[] lPermissions = {Manifest.permission.ACCESS_COARSE_LOCATION};
                activity.requestPermissions(lPermissions, PERMISSIONS_REQUEST_LOCATION);
                return;
            } else if (activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                String[] lPermissions = {Manifest.permission.ACCESS_FINE_LOCATION};
                activity.requestPermissions(lPermissions, PERMISSIONS_REQUEST_LOCATION);
                return;
            }
        }
        ToastUtils.longToast(R.string.location_permission_already_granted);
    }

    /**
     * Check and ask camera permission.
     */
    public void checkAskCameraPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {

                String[] lPermissions = {Manifest.permission.CAMERA};
                activity.requestPermissions(lPermissions, PERMISSIONS_REQUEST_CAMERA);
                return;
            }
        }
        ToastUtils.longToast(R.string.camera_permission_already_granted);
    }


    /**
     * Is all location permission granted boolean.
     *
     * @return the boolean
     */
    public boolean isAllLocationPermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ToastUtils.longToast(R.string.not_all_location_permission_granted);
                return false;
            }
        }
        ToastUtils.longToast(R.string.location_permission_granted);
        return true;
    }

    /**
     * Is camera permission granted boolean.
     *
     * @return the boolean
     */
    public boolean isCameraPermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                ToastUtils.longToast(R.string.camera_permission_denied);
                return false;
            }
        }
        ToastUtils.longToast(R.string.camera_permission_granted);
        return true;
    }

}
