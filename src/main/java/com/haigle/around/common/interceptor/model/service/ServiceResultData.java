package com.haigle.around.common.interceptor.model.service;

import lombok.ToString;

/**
 * 服务回参数实体
 * @author haigle
 * @date 2019/03/04 22:57
 */
@ToString
public class ServiceResultData<T> extends ServiceResult {

    private static final long serialVersionUID = -740780238124331993L;

    /**
     * 返回数据集
     */
    private T data;


    public ServiceResultData(boolean success, T data) {
        this.data = data;
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public ServiceResultData<T> setData(T data) {
        this.data = data;
        return this;
    }

}
