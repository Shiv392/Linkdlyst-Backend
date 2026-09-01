package com.example.Linkdlyst.Utils.ApiResponse;

public class GlobalApiResponse<T> {
    private boolean success;
    private String message;
    private T data;

    public GlobalApiResponse(){}

    public GlobalApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean getSuccess() {
        return success;
    }
    public String getMessage() {
        return message;
    }
    public T getData() {
        return data;
    }
}
