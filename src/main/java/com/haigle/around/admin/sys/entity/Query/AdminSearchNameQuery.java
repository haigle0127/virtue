package com.haigle.around.admin.sys.entity.Query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 分页搜索专用
 * @author haigle
 * @date 2019/8/21 15:02
 */
@Getter
@Setter
public class AdminSearchNameQuery {

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页")
    private int page;

    /**
     * 每页记录数
     */
    @ApiModelProperty(value = "每页记录数")
    private int pageSize;

    /**
     * 名字
     * @date 2019/8/21 15:10
     */
    @ApiModelProperty(value = "名称")
    private String name;

}
