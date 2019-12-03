package com.shahjahan.retrofitandroid;


import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;

import static com.shahjahan.retrofitandroid.ApiUtils.CLIENT_ID;
import static com.shahjahan.retrofitandroid.ApiUtils.CLIENT_SECRET;

/**
 * Created by shahjahan.samoon on 04/10/2017.
 */

public class ApiRepository {

    private ApiService apiService = ServiceGenerator.createService(ApiService.class, CLIENT_ID, CLIENT_SECRET);

    public void login(final Map<String, String> params , final ApiResponseCallback<AccessToken> apiResponseCallback) {
        Call<ApiResponse<AccessToken>> userAccessTokenCall = apiService.getAccessToken(params);
        userAccessTokenCall.enqueue(new ApiUtils.ApiResponseCallbackHandler<>(apiResponseCallback));
    }

    public void getUserList(final ApiResponseCallback<ArrayList<User>> apiResponseCallback) {
        apiService = ServiceGenerator.createService(ApiService.class, App.getContext().getAccessToken());
        Call<ApiResponse<ArrayList<User>>> userAccessTokenCall = apiService.getUserList();
        userAccessTokenCall.enqueue(new ApiUtils.ApiResponseCallbackHandler<>(apiResponseCallback));
    }

}
