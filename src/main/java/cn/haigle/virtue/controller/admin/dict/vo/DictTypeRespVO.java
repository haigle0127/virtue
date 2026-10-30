package cn.haigle.virtue.controller.admin.dict.vo;

import lombok.Data;

@Data
public class DictTypeRespVO {

    private Long id;
    private String name;
    private String type;
    private String status;
    private String remark;
    private String createTime;
}
