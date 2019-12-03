package com.shahjahan.retrofitandroid;

/**
 * Created by shahjahan.samoon on 15/05/2017.
 * ApiError is value object contains API errors
 *
 * @author shahjahan.samoon
 */

public class ApiError<T> {
    public String path;
    public String error;
    public String message;
    public String timestamp;
    public String code;

    public ApiError(String code, String error) {
        this.code = code;
        this.error = error;
    }


}
