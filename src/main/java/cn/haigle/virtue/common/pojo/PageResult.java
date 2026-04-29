package cn.haigle.virtue.common.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页结果
 * @author haigle
 * @date 2026/4/29 15:10
 */
@Data
public final class PageResult<T> implements Serializable {

    private List<T> list;

    private Long total;

    private Integer pageNo;

    public PageResult() {
    }

    public PageResult(List<T> list, Long total, Integer pageNo) {
        this.list = list;
        this.total = total;
        this.pageNo = pageNo;
    }

    public PageResult(Long total, Integer pageNo) {
        this.total = total;
        this.pageNo = pageNo;
    }

    public PageResult(List<T> list, Long total) {
        this.list = list;
        this.total = total;
    }

    public PageResult(Long total) {
        this.list = new ArrayList<>();
        this.total = total;
    }

    public static <T> PageResult<T> empty() {
        return new PageResult<>(0L);
    }

    public static <T> PageResult<T> empty(Long total) {
        return new PageResult<>(total);
    }

}
