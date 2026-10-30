package cn.haigle.virtue.controller.admin.dict.vo;

import lombok.Data;

@Data
public class DictDataRespVO {

    private Long id;
    private Integer sort;
    private String label;
    private String value;
    private String dictType;
    private String status;
    private String colorType;
    private String cssClass;
    private String remark;
    private String createTime;
}
