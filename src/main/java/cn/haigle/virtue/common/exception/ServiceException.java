package cn.haigle.virtue.common.exception;

import lombok.Getter;

/**
 * 业务异常
 *
 * @author haigle
 */
@Getter
public final class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 业务错误码
     *
     * @see CodeConstants
     */
    private final Integer code;

    /**
     * 错误提示
     */
    private final String message;

    public ServiceException(CodeConstants codeConstants) {
        this.code = codeConstants.getValue();
        this.message = codeConstants.getName();
    }

    public ServiceException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ServiceException(String message) {
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
