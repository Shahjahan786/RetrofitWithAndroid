package com.shahjahan.retrofitandroid;

import android.app.Application;

public class App extends Application {

    private static App context;

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    private AccessToken accessToken;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static App getContext(){
        return context;
    }

}
