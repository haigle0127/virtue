package com.haigle.around.common.interceptor.model;


import lombok.ToString;
import java.text.MessageFormat;

/**
 * 接口回参数实体-国际化
 * @author haigle
 * @date 2019/03/04 22:57
 */
@ToString
public class ApiResultDataI18n<T> extends ApiResultI18n {

    private static final long serialVersionUID = -740780238124331993L;

    /**
     * 返回数据集
     */
    private T data;


    public ApiResultDataI18n(boolean success, T data) {
        this.data = data;
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public ApiResultDataI18n<T> setData(T data) {
        this.data = data;
        return this;
    }


    public ApiResultDataI18n<T> setMessage(String message, boolean success) {
        this.code = 200;
        this.message = getMessage(message);
        this.success = success;
        return this;
    }

    public ApiResultDataI18n<T> setMessage(String message, Object... obj) {
        this.message = MessageFormat.format(getMessage(message), obj);
        return this;
    }

}
