package com.haigle.around.common.interceptor.model;


import lombok.ToString;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.annotation.Resource;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Locale;

/**
 * 接口回参数实体
 * @author haigle
 * @date 2019/03/04 22:57
 */
@ToString
public class ApiResult<T> implements Serializable {

    private static final long serialVersionUID = -740780238124331993L;
    private static final Object OBJECT = null;

    @Resource(name = "messageSource")
    private MessageSource source;

    private String messages(String key, Object... args) {

        // 消息的参数化和国际化配置
        Locale locale = LocaleContextHolder.getLocale();
        return source.getMessage(key, args, locale);
    }

    /**
     * 获取i18n
     * 调用此方法getMessage(message)，即可得到国际化字符串
     */
    private String getMessage(String key) {
        return messages(key, OBJECT);
    }

    private int code = 200;
    private String message = "";
    private boolean success;

    /**
     * 返回数据集
     */
    private T data;

    public ApiResult(int code, String message, boolean success) {
        this.code = code;
        this.message = getMessage(message);
        this.success = success;
    }

    public ApiResult(String message, boolean success) {
        this.message = getMessage(message);
        this.success = success;
    }

    public ApiResult(boolean success, T data) {
        this.data = data;
        this.success = success;
    }

    public ApiResult(boolean success) {
        this.success = success;
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

    public ApiResult<T> setMessage(String message) {
        this.message = getMessage(message);
        return this;
    }

    public ApiResult<T> setMessage(int code, String message) {
        this.code = code;
        this.message = getMessage(message);
        return this;
    }

    public ApiResult<T> setMessage(String message, boolean success) {
        this.code = 200;
        this.message = getMessage(message);
        this.success = success;
        return this;
    }

    public ApiResult<T> setMessage(String message, Object... obj) {
        this.message = MessageFormat.format(getMessage(message), obj);
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
