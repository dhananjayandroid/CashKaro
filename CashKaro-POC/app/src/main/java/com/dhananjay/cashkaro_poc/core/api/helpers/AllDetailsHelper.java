package com.dhananjay.cashkaro_poc.core.api.helpers;

import android.content.Context;
import android.os.Bundle;

import com.dhananjay.cashkaro_poc.core.api.ApiManager;
import com.dhananjay.cashkaro_poc.core.api.exception.ResponseException;
import com.dhananjay.cashkaro_poc.core.api.service.ServiceConsts;


/**
 * This class extends @{@link BaseHelper} and
 * provides help methods for all details command
 *
 * @author Dhananjay Kumar
 */
public class AllDetailsHelper extends BaseHelper {

    /**
     * Instantiates a new All details helper.
     *
     * @param context the context
     */
    public AllDetailsHelper(Context context) {
        super(context);
    }

    /**
     * Gets all details.
     *
     * @param bundle the bundle
     * @return the all details
     * @throws ResponseException the response exception
     */
    public Bundle getAllDetails(Bundle bundle) throws ResponseException {
        if(bundle == null) bundle = new Bundle(); //Temporary
        String emailId = bundle.getString(ServiceConsts.EXTRA_EMAIL);
        return ApiManager.getAllDetails(emailId, bundle);
    }
}
