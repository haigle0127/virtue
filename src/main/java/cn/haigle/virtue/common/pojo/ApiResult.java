package cn.haigle.virtue.common.pojo;

import cn.haigle.virtue.common.interceptor.exception.enums.CodeConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.io.Serializable;

/**
 * 接口通用返回
 * @param <T> 数据泛型
 * @author haigle
 * @date 2019/9/6 15:18
 */
@Data
public class ApiResult<T> implements Serializable {

    @Schema(description = "编码CODE", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer code;

    @Schema(description = "提示", requiredMode = Schema.RequiredMode.REQUIRED)
    private String message;

    @Schema(description = "返回数据集", requiredMode = Schema.RequiredMode.REQUIRED)
    private T data;

    public static <T> ApiResult<T> ok() {
        return restResult(CodeConstants.OK.getValue(), CodeConstants.OK.getName(), null);
    }

    public static <T> ApiResult<T> ok(T data) {
        return restResult(CodeConstants.OK.getValue(), CodeConstants.OK.name(), data);
    }

    public static <T> ApiResult<T> ok(String message, T data) {
        return restResult(CodeConstants.OK.getValue(), message, data);
    }

    public static <T> ApiResult<T> fail() {
        return restResult(CodeConstants.FAIL.getValue(), CodeConstants.FAIL.name(), null);
    }

    public static <T> ApiResult<T> fail(String message) {
        return restResult(CodeConstants.FAIL.getValue(), message, null);
    }

    public static <T> ApiResult<T> fail(String message, T data) {
        return restResult(CodeConstants.FAIL.getValue(), message, data);
    }

    public static <T> ApiResult<T> code(int code, String message) {
        return restResult(code, message, null);
    }

    private static <T> ApiResult<T> restResult(Integer code, String message, T data) {
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setCode(code);
        apiResult.setMessage(message);
        apiResult.setData(data);
        return apiResult;
    }

}
