package com.fantasy.practice.service.pipeline;

/**
 * Created by jiaji on 16/12/27.
 */
public class PipeException extends RuntimeException {

    private int errorCode;

    public PipeException() {
        super();
    }

    public PipeException(String message) {
        super(message);
    }

    public PipeException(String message, Throwable cause) {
        super(message, cause);
    }

    public PipeException(Throwable cause) {
        super(cause);
    }

    public PipeException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode.getCode();
    }

    public PipeException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode.getCode();
    }
}
