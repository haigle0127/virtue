package com.haigle.around.common.interceptor.model.service;

import java.io.Serializable;

/**
 * 服务返回数据
 * @author haigle
 * @date 2019/9/6 15:18
 */
public class ServiceResult implements Serializable {

    protected int code = 200;
    protected String message = "";
    protected boolean success;

    public ServiceResult(int code, String message, boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    public ServiceResult(String message, boolean success) {
        this.message = message;
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

    public ServiceResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public ServiceResult setMessage(int code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }

    public ServiceResult setMessage(String message, boolean success) {
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

}
