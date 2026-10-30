package cn.haigle.virtue.common.exception;

import lombok.Getter;

/**
 * 服务器异常
 *
 * @author haigle
 */
@Getter
public final class ServerException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     *
     * @see CodeConstants
     */
    private final Integer code;

    /**
     * 错误提示
     */
    private final String message;

    public ServerException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ServerException(String message) {
        this.code = CodeConstants.FAIL.getValue();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }

}
