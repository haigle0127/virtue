package cn.haigle.around.common.interceptor.model;

import javax.annotation.Resource;

/**
 * I18n 国际化装配
 * @author haigle
 * @date 2019-08-04 20:39
 */
public class BaseI18n {

    /**
     * 操作成功!
     */
    protected static final String SUCCESS = "common.success";

    /**
     * 新增成功
     */
    protected static final String SAVE_SUCCESS = "common.save.success";

    /**
     * 修改成功
     */
    protected static final String UPDATE_SUCCESS = "common.update.success";

    /**
     * 删除成功
     */
    protected static final String DELETE_SUCCESS = "common.delete.success";

    @Resource(name = "apiResultI18n")
    protected ApiResultI18n apiResultI18n;

}
