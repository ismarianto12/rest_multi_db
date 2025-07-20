package com.restfullApi.multidb.utils;

public class CustomResponse<T> {

    public T data;
    public int code;
    public String message;
    public CustomResponse(T data, int code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

}
