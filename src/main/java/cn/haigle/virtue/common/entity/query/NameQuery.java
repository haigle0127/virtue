package cn.haigle.virtue.common.entity.query;

import lombok.Getter;
import lombok.Setter;

/**
 * 分页搜索专用
 * @author haigle
 * @date 2019/8/21 15:02
 */
@Getter
@Setter
public class NameQuery {

    /**
     * 当前页
     */
    private int page;

    /**
     * 每页记录数
     */
    private int pageSize;

    /**
     * 名字
     * @date 2019/8/21 15:10
     */
    private String name;

}
