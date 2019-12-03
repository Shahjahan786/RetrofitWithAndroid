package com.shahjahan.retrofitandroid;

import com.google.gson.stream.MalformedJsonException;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;


/**
 * Created by shahjahan.samoon on 16/05/2017.
 */

public class ApiUtils {


    public static final String CLIENT_ID = "test-client";
    public static final String CLIENT_SECRET = "secret";

    private ApiUtils() {
        throw new UnsupportedOperationException("Constructor call not supported");
    }


    public static <T> ApiResponse<T> parseResponseErrorBody(Response<T> response) {
        Converter<ResponseBody, ApiResponse<T>> converter =
                ServiceGenerator.getRetrofit().responseBodyConverter(ApiResponse.class, new Annotation[0]);

        ApiResponse<T> errorResponse;
        try {
            errorResponse = converter.convert(response.errorBody());
        } catch (MalformedJsonException e) {
            errorResponse = new ApiResponse<>();
            errorResponse.setMessage("Internal Server Error");
        } catch (IOException e) {
            errorResponse = new ApiResponse<>();
            errorResponse.setMessage("Internal Server Error");

        }

        return errorResponse;
    }


    public static <T> ApiError getApiError(Response<T> response) {
        ApiResponse<T> parsedResponse = parseResponseErrorBody(response);
        ApiError apiError = parsedResponse.getError();
        return parsedResponse.getError();

    }

    public static <T> void handleOnApiResponse(Response<ApiResponse<T>> response, ApiResponseCallback apiResponseCallback) {
        if (response.isSuccessful()) {

            ApiResponse<T> apiResponse = parseResponseBody(response);
            apiResponseCallback.onResponseSuccess(apiResponse);
        } else {
            ApiError error = getApiError(response);
            apiResponseCallback.onApiError(error);
        }
    }


    public static <T> ApiResponse<T> parseResponseBody(Response<ApiResponse<T>> response) {
        return response.body();
    }


    public static class ApiResponseCallbackHandler<T> implements Callback<ApiResponse<T>> {
        ApiResponseCallback<T> apiResponseCallback;

        public ApiResponseCallbackHandler(ApiResponseCallback<T> apiResponseCallback) {
            this.apiResponseCallback = apiResponseCallback;
        }

        @Override
        public void onResponse(Call<ApiResponse<T>> call, Response<ApiResponse<T>> response) {
            handleOnApiResponse(response, apiResponseCallback);
        }

        @Override
        public void onFailure(Call<ApiResponse<T>> call, Throwable t) {

            apiResponseCallback.onRequestFailure(t);
        }

    }

}
