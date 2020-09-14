package cn.haigle.around.common.interceptor.model;

import java.io.Serializable;

/**
 * 接口回参数实体
 * @author haigle
 * @date 2019/9/6 15:18
 */
public class ApiResult<T> implements Serializable {

    private int code = 200;
    private String message = "";
    private boolean success = true;

    /**
     * 返回数据集
     */
    private T data;

    public ApiResult(int code, String message, boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    public ApiResult(int code, String message, T data, boolean success) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.success = success;
    }

    public ApiResult(T data, boolean success) {
        this.data = data;
        this.success = success;
    }

    public ApiResult(T data, String message) {
        this.data = data;
        this.message = message;
    }

    public ApiResult() {
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

    public ApiResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public ApiResult<T> setMessage(int code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }

    public ApiResult<T> setMessage(String message, boolean success) {
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

    public ApiResult<T> setData(T data) {
        this.data = data;
        return this;
    }

}

