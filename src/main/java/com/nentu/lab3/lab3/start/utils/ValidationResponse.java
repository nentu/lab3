package com.nentu.lab3.lab3.start.utils;

public class ValidationResponse {
    public static final ValidationResponse successResp = new ValidationResponse(true, "");
    public boolean success;
    public String msg;

    public ValidationResponse(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }
}
