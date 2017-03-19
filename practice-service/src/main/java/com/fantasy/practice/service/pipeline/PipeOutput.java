package com.fantasy.practice.service.pipeline;

/**
 * Created by jiaji on 16/12/23.
 */
public class PipeOutput<T> {
    private PipeException pipeException;
    private T result;
    private String errorMsg;
    private int errorCode;

    public String getErrorMsg() {
        return errorMsg;
    }

    public PipeOutput setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public PipeOutput setErrorCode(int errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public PipeException getPipeException() {
        return pipeException;
    }

    public PipeOutput setPipeException(PipeException pipeException) {
        this.pipeException = pipeException;
        return this;
    }

    public T getResult() {
        return result;
    }

    public PipeOutput setResult(T result) {
        this.result = result;
        return this;
    }
}
