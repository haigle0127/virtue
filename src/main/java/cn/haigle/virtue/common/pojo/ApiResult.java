package cn.haigle.virtue.common.pojo;

import cn.haigle.virtue.common.exception.CodeConstants;
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

    private Integer code;

    private String msg;

    private T data;

    public static <T> ApiResult<T> ok() {
        return restResult(CodeConstants.OK.getValue(), CodeConstants.OK.getName(), null);
    }

    public static <T> ApiResult<T> ok(T data) {
        return restResult(CodeConstants.OK.getValue(), CodeConstants.OK.name(), data);
    }

    public static <T> ApiResult<T> ok(String msg, T data) {
        return restResult(CodeConstants.OK.getValue(), msg, data);
    }

    public static <T> ApiResult<T> fail() {
        return restResult(CodeConstants.FAIL.getValue(), CodeConstants.FAIL.getName(), null);
    }

    public static <T> ApiResult<T> fail(String msg) {
        return restResult(CodeConstants.FAIL.getValue(), msg, null);
    }

    public static <T> ApiResult<T> fail(String msg, T data) {
        return restResult(CodeConstants.FAIL.getValue(), msg, data);
    }

    public static <T> ApiResult<T> code(int code, String msg) {
        return restResult(code, msg, null);
    }

    private static <T> ApiResult<T> restResult(Integer code, String msg, T data) {
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setCode(code);
        apiResult.setMsg(msg);
        apiResult.setData(data);
        return apiResult;
    }

}
