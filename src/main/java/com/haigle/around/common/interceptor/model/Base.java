package com.haigle.around.common.interceptor.model;

import javax.annotation.Resource;

/**
 * I18n 国际化装配
 * @author haigle
 * @date 2019-08-04 20:39
 */
public class Base {

    /**
     * 操作成功!
     */
    protected static final String SUCCESS = "成功";

    /**
     * 新增成功
     */
    protected static final String SAVE_SUCCESS = "添加成功";

    /**
     * 修改成功
     */
    protected static final String UPDATE_SUCCESS = "修改成功";

    /**
     * 删除成功
     */
    protected static final String DELETE_SUCCESS = "删除成功";

    @Resource(name = "apiResult")
    protected ApiResult apiResult;

}
