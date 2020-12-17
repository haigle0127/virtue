package cn.haigle.around.common.interceptor.model;

import java.io.Serializable;

import cn.haigle.around.common.interceptor.model.message.CodeStatus;
import lombok.ToString;

import java.io.Serializable;

/**
 * 接口回参数实体
 * @author haigle
 * @date 2019/9/6 15:18
 */
@ToString
public class ApiResult<T> implements Serializable {

    private static final long serialVersionUID = -740780238124331993L;

    private int code;
    private String message;

    /**
     * 返回数据集
     */
    private T data;

    public ApiResult(CodeStatus status) {
        this(null, status);
    }

    public ApiResult(T data, CodeStatus status) {
        this.code = status.value();
        this.message = status.name();
//        this.message = status.reasonPhrase();
        this.data = data;
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

    public T getData() {
        return data;
    }

    public ApiResult<T> setData(T data) {
        this.data = data;
        return this;
    }

}
