package com.shahjahan.retrofitandroid;

/**
 * Created by shahjahan.samoon on 15/05/2017.
 * Response is value object contains API response data
 *
 * @author shahjahan.samoon
 */

public class ApiResponse<T> {

    private String status;
    private ApiError error;
    private T data;
    private String message;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ApiError getError() {
        return error;
    }

    public void setError(ApiError error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ApiResponse() {

    }



}
