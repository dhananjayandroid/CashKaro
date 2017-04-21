package com.dhananjay.cashkaro_poc.core.api;


import android.util.Log;


import com.dhananjay.cashkaro_poc.core.api.exception.ResponseException;
import com.dhananjay.cashkaro_poc.core.api.models.JsonResponse;
import com.dhananjay.cashkaro_poc.core.api.service.ServiceConsts;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by Dhananjay on 05-01-2017.
 */
class RequestExecutor {

    public JsonResponse perform(Call<JsonResponse> call) throws ResponseException {
        JsonResponse jsonResponse;
        try {
            Response<JsonResponse> response;
            try {
                response = call.execute();
            } catch (Exception e) {
                Log.i("Exception", e.getMessage());
                throw new ResponseException("Server is not responding");
            }
            if (response != null && !response.isSuccessful() && response.errorBody() != null) {
                JsonResponse error;
                try {
                    Converter<ResponseBody, JsonResponse> errorConverter =
                            ApiClient.getClient().responseBodyConverter(JsonResponse.class, new Annotation[0]);
                    error = errorConverter.convert(response.errorBody());
                } catch (Exception e) {
                    throw new ResponseException("Error while connecting to server");
                }
                if (error != null)
                    throw new ResponseException(error.getReason());
            }
            assert response != null;
            jsonResponse = response.body();
            if (!jsonResponse.getStatus().equalsIgnoreCase(ServiceConsts.SUCCESS))
                throw new ResponseException(jsonResponse.getReason());
        } catch (IOException var4) {
            Log.i("IOException", var4.getMessage());
            throw new ResponseException(var4.getMessage());
        }
        return jsonResponse;
    }
}
