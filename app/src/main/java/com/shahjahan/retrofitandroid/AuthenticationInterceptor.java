package com.shahjahan.retrofitandroid;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by shahjahan.samoon on 15/05/2017.
 * AuthenticationInterceptor adds authorization header to the request
 * @author shahjahan.samoon
 */


public class AuthenticationInterceptor implements Interceptor {

    private AccessToken accessToken = null;


    public AuthenticationInterceptor() {

    }

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder builder = original.newBuilder();

        if(accessToken != null){
            builder.addHeader("Authorization", accessToken.getTokenType() + " " + accessToken.getAccessToken() );
        }

        Request request = builder.build();
        return chain.proceed(request);
    }
}
