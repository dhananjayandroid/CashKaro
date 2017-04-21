package com.dhananjay.cashkaro_poc.core.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This class provides client for all api calls
 *
 * @author Dhananjay Kumar
 */
public class ApiClient {

    private static final String BASE_URL = "http://127.0.0.0"; // Change to the server address
    private static Retrofit retrofit = null;

    /**
     * Returns {@link Retrofit} client
     *
     * @return {@link Retrofit} client
     */
    public static Retrofit getClient() {
        if (retrofit == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            OkHttpClient client = httpClient.build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
