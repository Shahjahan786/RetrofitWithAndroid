package com.shahjahan.retrofitandroid;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shahjahan.samoon on 15/05/2017.
 * Value object for Access Token
 *
 * @author shahjahan.samoon
 */

public class AccessToken {

    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("token_type")
    private String tokenType;
    @SerializedName("refresh_token")
    private String refreshToken;
    @SerializedName("expires_in")
    private long expiresIn;

    private long updatedAt = System.currentTimeMillis();

    public AccessToken(String accessToken, String tokenType) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    String getTokenType() {

        // OAuth requires uppercase Authorization HTTP header value for token type
        if (!Character.isUpperCase(tokenType.charAt(0))) {
            tokenType =
                    Character
                            .toString(tokenType.charAt(0))
                            .toUpperCase() + tokenType.substring(1);
        }

        return tokenType;
    }

    public boolean isTokenExpired(){
        boolean isExpired = false;

        long currentTime = System.currentTimeMillis();
        long milliSecondsToExpire = (expiresIn * 1000);
        long timeToExpire = updatedAt + milliSecondsToExpire;

        long timeRemaining = timeToExpire - currentTime;

        isExpired = timeRemaining <= 0;



        return isExpired;
    }

    public long getTimeToExpire(){

        long currentTime = System.currentTimeMillis();
        long milliSecondsToExpire = (expiresIn * 1000);
        long timeToExpire = updatedAt + milliSecondsToExpire;

        long timeRemaining = timeToExpire - currentTime;

        return timeRemaining;
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "accessToken='" + accessToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", expiresIn=" + expiresIn +
                ", updatedAt=" + updatedAt +
                ", timeRemaining=" + getTimeToExpire() +
                '}';
    }
}

