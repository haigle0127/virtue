package cn.haigle.virtue.common.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Schema(description = "分页结果")
@Data
public final class PageResult<T> implements Serializable {

    @Schema(description = "数据", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<T> list;

    @Schema(description = "总量", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long total;

    @Schema(description = "当前页码", requiredMode = Schema.RequiredMode.REQUIRED)
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
