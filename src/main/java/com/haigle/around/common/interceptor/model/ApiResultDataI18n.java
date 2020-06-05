package com.haigle.around.common.interceptor.model;


import lombok.ToString;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.Locale;

/**
 * 接口回参数实体-国际化
 * @author haigle
 * @date 2019/03/04 22:57
 */
@ToString
public class ApiResultDataI18n<T> extends ApiResultI18n {

    private static final long serialVersionUID = -740780238124331993L;
    private static final Object OBJECT = null;


    private int code = 200;
    private String message = "";
    private boolean success;

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

}
