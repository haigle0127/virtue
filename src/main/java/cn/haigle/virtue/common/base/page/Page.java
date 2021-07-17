package cn.haigle.virtue.common.base.page;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页
 * @author haigle
 * @date 2018/12/5 15:30
 */
@Data
@Builder
public class Page<T> implements Serializable {

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
     * 总记录数
     */
    @ApiModelProperty(hidden = true)
    private int total;

    /**
     * 查询的数据
     */
    @ApiModelProperty(hidden = true)
    private List<T> rows;

    /**
     * 获取总页数
     * @return int
     */
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    public int getTotalPage() {
        return (total % pageSize == 0 ? (total / pageSize) : (total / pageSize + 1));
    }

    /**
     * 获取开始查询的记录数
     * @return int
     */
    @JsonIgnore
    public int getCol() {
        return (page - 1) * pageSize;
    }

}
