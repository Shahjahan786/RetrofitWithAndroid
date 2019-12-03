package com.shahjahan.retrofitandroid;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by shahjahan.samoon on 15/05/2017.
 * TokenService is used by ServiceGenerator to make api calls related to access tokens
 * @author shahjahan.samoon
 */

public interface ApiService {

    /**
     * Used for obtaining client access token from API server
     * @param grantType
     * @return
     */

    @FormUrlEncoded
    @POST("oauth/token")
    Call<ApiResponse<AccessToken>> getAccessToken(
            @Field("grant_type") String grantType);


    /**
     * Used for obtaining user access token from API server
     * @param params
     *
     * @return
     */
    @FormUrlEncoded
    @POST("oauth/token?grant_type=password")
    Call<ApiResponse<AccessToken>> getAccessToken(
            @FieldMap Map<String, String> params
    );

    @FormUrlEncoded
    @POST("oauth/token?grant_type=refresh_token")
    Call<ApiResponse<AccessToken>> getAccessTokenByRefreshToken(
            @Field("refresh_token") String refresh_token
    );


    @GET("users")
    Call<ApiResponse<ArrayList<User>>> getUserList();


}
