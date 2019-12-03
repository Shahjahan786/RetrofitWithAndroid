package com.shahjahan.retrofitandroid;

/**
 * Created by shahjahan.samoon on 11/10/2017.
 */

public interface ApiResponseCallback<T> {
    void onResponseSuccess(ApiResponse<T> response);

    void onRequestFailure(Throwable t);

    void onApiError(ApiError apiError);
}

