package com.dhananjay.cashkaro_poc.core.api;


import com.dhananjay.cashkaro_poc.core.api.models.JsonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;


/**
 * This class contains all api actions
 *
 * @author Dhananjay Kumar
 */
public interface ApiInterface {

    @Headers("Client: mobile")
    @GET("/my_data")
    Call<JsonResponse> getMyData(@Path("userEmail") String userEmail);

}