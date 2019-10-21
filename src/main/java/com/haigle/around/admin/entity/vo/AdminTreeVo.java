package com.haigle.around.admin.entity.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 权限菜单总树结构
 * @author haigle
 * @date 2019-06-09 14:15
 */
@Data
@Builder
public class AdminTreeVo {

    private Long id;
    private String name;
    private boolean hasChildren;

    private List<AdminTreeVo> children;

}
