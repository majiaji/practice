package com.fantasy.practice.service.pipeline;

/**
 * Created by jiaji on 16/12/27.
 */
public enum ErrorCode {
    SYSTEM_BUG(-1, "系统bug");

    int code;
    String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public ErrorCode setMessage(String message) {
        this.message = message;
        return this;
    }

    public int getCode() {
        return code;
    }

    public ErrorCode setCode(int code) {
        this.code = code;
        return this;
    }
}
