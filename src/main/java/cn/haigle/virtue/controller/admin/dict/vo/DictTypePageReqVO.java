package cn.haigle.virtue.controller.admin.dict.vo;

import cn.haigle.virtue.common.pojo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DictTypePageReqVO extends PageParam {

    private String name;

    private String type;

    private String status;
}
