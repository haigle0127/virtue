package cn.haigle.virtue.controller.admin.dict.vo;

import cn.haigle.virtue.common.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DictDataPageReqVO extends PageParam {

    private String dictType;

    private String label;

    private String status;
}
