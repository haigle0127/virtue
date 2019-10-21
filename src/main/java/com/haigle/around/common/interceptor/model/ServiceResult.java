package com.haigle.around.common.interceptor.model;

import java.io.Serializable;

/**
 * 服务返回数据
 * @author haigle
 * @date 2019/9/6 15:18
 */
public class ServiceResult<T> implements Serializable {

    private int code = 200;
    private String message = "";
    private boolean success;

    /**
     * 返回数据集
     */
    private T data;

    public ServiceResult(int code, String message, boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    public ServiceResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public ServiceResult(boolean success, T data) {
        this.data = data;
        this.success = success;
    }

    public ServiceResult(boolean success) {
        this.success = success;
    }

    public ServiceResult() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(int code) {
        this.message = message;
    }

    public ServiceResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public ServiceResult<T> setMessage(int code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }

    public ServiceResult<T> setMessage(String message, boolean success) {
        this.message = message;
        this.success = success;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public ServiceResult<T> setData(T data) {
        this.data = data;
        return this;
    }

}
