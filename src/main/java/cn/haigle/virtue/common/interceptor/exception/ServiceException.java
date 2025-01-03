package cn.haigle.virtue.common.interceptor.exception;

import cn.haigle.virtue.common.interceptor.exception.enums.CodeConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 业务逻辑异常 Exception
 */
@Data
@EqualsAndHashCode(callSuper = true)
public final class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 业务错误码
     *
     * @see CodeConstants
     */
    private Integer code;
    /**
     * 错误提示
     */
    private String message;

    /**
     * 空构造方法，避免反序列化问题
     */
    public ServiceException() {
    }

    public ServiceException(CodeConstants codeConstants) {
        this.code = codeConstants.getValue();
        this.message = codeConstants.getName();
    }

    public ServiceException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ServiceException(String message) {
        this.code = 500;
        this.message = message;
    }

    @Override
    public String getMessage()
    {
        return message;
    }

    public Integer getCode()
    {
        return this.code;
    }

    public ServiceException setMessage(String message)
    {
        this.message = message;
        return this;
    }

}
