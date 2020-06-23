package com.haigle.around.common.interceptor.model;


import lombok.ToString;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.annotation.Resource;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Locale;

/**
 * 接口回参数实体-国际化
 * @author haigle
 * @date 2019/03/04 22:57
 */
@ToString
public class ApiResultI18n implements Serializable {

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
    protected String getMessage(String key) {
        return messages(key, OBJECT);
    }

    protected int code = 200;
    protected String message = "";
    protected boolean success;

    public ApiResultI18n(int code, String message, boolean success) {
        this.code = code;
        this.message = getMessage(message);
        this.success = success;
    }

    public ApiResultI18n(String message, boolean success) {
        this.message = getMessage(message);
        this.success = success;
    }

    public ApiResultI18n(boolean success) {
        this.success = success;
    }

    public ApiResultI18n() {
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

    public ApiResultI18n setMessage(String message) {
        this.message = getMessage(message);
        return this;
    }

    public ApiResultI18n setMessage(int code, String message) {
        this.code = code;
        this.message = getMessage(message);
        return this;
    }

    public ApiResultI18n setMessage(String message, boolean success) {
        this.code = 200;
        this.message = getMessage(message);
        this.success = success;
        return this;
    }

    public ApiResultI18n setMessage(int code, String message, boolean success) {
        this.code = code;
        this.message = getMessage(message);
        this.success = success;
        return this;
    }

    public ApiResultI18n setMessage(String message, Object... obj) {
        this.message = MessageFormat.format(getMessage(message), obj);
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
