package cn.haigle.virtue.common.interceptor.model;

import java.io.Serializable;
import cn.haigle.virtue.common.interceptor.model.message.CodeStatus;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 接口回参数实体
 * @author haigle
 * @date 2019/9/6 15:18
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ApiResult<T> implements Serializable {

    private static final long serialVersionUID = -740780238124331993L;

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String message;

    /**
     * 返回数据集
     */
    @Getter
    @Setter
    private T data;

    public static <T> ApiResult<T> ok() {
        return restResult(CodeStatus.OK, null, null);
    }

    public static <T> ApiResult<T> ok(T data) {
        return restResult(CodeStatus.OK, null, data);
    }

    public static <T> ApiResult<T> ok(String message, T data) {
        return restResult(CodeStatus.OK, message, data);
    }

    public static <T> ApiResult<T> fail() {
        return restResult(CodeStatus.FAIL, null, null);
    }

    public static <T> ApiResult<T> fail(String message) {
        return restResult(CodeStatus.FAIL, message, null);
    }

    public static <T> ApiResult<T> fail(String message, T data) {
        return restResult(CodeStatus.FAIL, message, data);
    }

    public static <T> ApiResult<T> code(int code, String message) {
        return restResult(code, message, null);
    }

    private static <T> ApiResult<T> restResult(int code, String message, T data) {
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setCode(code);
        apiResult.setMessage(message);
        apiResult.setData(data);
        return apiResult;
    }

}
