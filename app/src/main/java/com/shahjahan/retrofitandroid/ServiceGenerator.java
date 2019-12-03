package com.shahjahan.retrofitandroid;

import android.text.TextUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shahjahan.samoon on 15/05/2017.
 * Creates Retrofit Client
 *
 * @author shahjahan.samoon
 */

public class ServiceGenerator {

    public static final String BASE_URL = "http://192.168.8.104:8080";
    private static OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
    private static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
    private static AuthenticationInterceptor authenticationInterceptor = new AuthenticationInterceptor();


    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    private static OkHttpClient.Builder getOkHttpClientBuilder() {

        return httpClientBuilder;
    }

    // HTTP Request Logging
    private ServiceGenerator() {
        throw new UnsupportedOperationException("Constructor call not supported");
    }

    /**
     * To get Retrofit Object
     *
     * @return Retrofit
     */
    public static Retrofit getRetrofit() {
        return retrofit;
    }

    /**
     * Create Retrofit Service without authenticationInterceptor interceptor
     *
     * @param serviceClass
     * @param <S>
     * @return
     */
    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null);
    }

    /**
     * Create Retrofit Service with authenticationInterceptor interceptor using client credentials
     *
     * @param serviceClass
     * @param clientId
     * @param clientSecret
     * @param <S>
     * @return
     */
    public static <S> S createService(Class<S> serviceClass, String clientId, String clientSecret) {
        if (!TextUtils.isEmpty(clientId)
                && !TextUtils.isEmpty(clientSecret)) {
            String authToken = Credentials.basic(clientId, clientSecret);

            String tokenType = authToken.split(" ")[0];
            String token = authToken.split(" ")[1];
            AccessToken accessToken = new AccessToken(token, tokenType);

            return createService(serviceClass, accessToken);
        }

        return createService(serviceClass, null);
    }

    /**
     * Create Retrofit Service with authenticationInterceptor interceptor using access token
     *
     * @param serviceClass
     * @param accessToken
     * @param <S>
     * @return
     */
    public static <S> S createService(
            Class<S> serviceClass, final AccessToken accessToken) {

        // Authentication Interceptor
        authenticationInterceptor.setAccessToken(accessToken);
        if (accessToken != null) {

            authenticationInterceptor.setAccessToken(accessToken);

            if (!httpClientBuilder.interceptors().contains(authenticationInterceptor)) {
                httpClientBuilder.addInterceptor(authenticationInterceptor);
            }

        }

        // Logging Interceptor
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (BuildConfig.DEBUG && !httpClientBuilder.interceptors().contains(httpLoggingInterceptor)) {
            httpClientBuilder.addInterceptor(httpLoggingInterceptor);
        }


        httpClientBuilder.readTimeout(20, TimeUnit.SECONDS);
        httpClientBuilder.connectTimeout(20, TimeUnit.SECONDS);

        builder.client(httpClientBuilder.build());
        retrofit = builder.build();

        return retrofit.create(serviceClass);
    }


}
