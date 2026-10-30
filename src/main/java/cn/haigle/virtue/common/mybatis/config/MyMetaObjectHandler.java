package cn.haigle.virtue.common.mybatis.config;

import cn.haigle.virtue.common.security.util.SecurityUtils;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * MyBatis-Plus 公共字段自动填充（createTime/updateTime/createBy/updateBy）
 * <p>仅在字段为空时填充，不覆盖业务代码显式设置的值</p>
 *
 * @author haigle
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        fillIfNull(metaObject, "createTime", now);
        fillIfNull(metaObject, "updateTime", now);
        Long userId = SecurityUtils.getUserId();
        fillIfNull(metaObject, "createBy", userId);
        fillIfNull(metaObject, "updateBy", userId);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        fillIfNull(metaObject, "updateTime", LocalDateTime.now());
        fillIfNull(metaObject, "updateBy", SecurityUtils.getUserId());
    }

    private void fillIfNull(MetaObject metaObject, String fieldName, Object value) {
        if (value == null || !metaObject.hasSetter(fieldName)) {
            return;
        }
        if (metaObject.getValue(fieldName) == null) {
            metaObject.setValue(fieldName, value);
        }
    }

}
