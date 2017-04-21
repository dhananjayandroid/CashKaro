package com.dhananjay.cashkaro_poc.core.api;

import android.os.Bundle;

import com.dhananjay.cashkaro_poc.core.api.exception.ResponseException;
import com.dhananjay.cashkaro_poc.core.api.service.ServiceConsts;
import com.dhananjay.cashkaro_poc.utils.DemoDataCreatorUtils;


/**
 * This class acts as a manager to perform all the api actions
 *
 * @author Dhananjay Kumar
 */
public class ApiManager {

    /**
     * Provides all the necessary details to app
     *
     * @param emailId        the email id
     * @param returnedBundle the returned bundle
     * @return all details
     * @throws ResponseException the response exception
     */
    public static Bundle getAllDetails(String emailId, Bundle returnedBundle) throws ResponseException {
        /** We can call our service api to get all the required details**/
//        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
//        Call<JsonResponse> call = apiInterface.getMyData(emailId);
//        JsonResponse jsonResponse = new RequestExecutor().perform(call);
        returnedBundle.putSerializable(ServiceConsts.EXTRA_TOP_OFFERS, DemoDataCreatorUtils.createDemoTopOffers());
        returnedBundle.putSerializable(ServiceConsts.EXTRA_OFFERS, DemoDataCreatorUtils.createDemoOffers());
        returnedBundle.putSerializable(ServiceConsts.EXTRA_TOP_CATEGORY, DemoDataCreatorUtils.createDemoTopCategories());
        return returnedBundle;
    }


}
