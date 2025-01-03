package cn.haigle.virtue.common.interceptor.advice;

import cn.dev33.satoken.exception.NotPermissionException;
import cn.haigle.virtue.common.interceptor.exception.ServiceException;
import cn.haigle.virtue.common.pojo.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

import static cn.haigle.virtue.common.interceptor.exception.enums.CodeConstants.NOT_PERMISSION_EXPIRED;

/**
 * 通用校验放回校验
 * @author haigle
 * @date 2018/11/12 13:10
 */
@RestControllerAdvice
public class ControllerValidateAdvice {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final static String DEV = "dev";
    private final static String ERROR_TITLE = "error";

    /**
     * 表单验证错误 同意错误码 901
     * 用 org.hibernate.validator 验证时， 加入@ExceptionHandler(BindException.class)异常处理
     * @return ApiResult
     * @author haigle
     * @date 2018/11/28 10:39
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResult<Objects> bindException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        return ApiResult.code(901, Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
    }

    /**
     * 服务器异常，500
     * @param e 错误信息
     * @return ApiResult
     * @author haigle
     * @date 2018/11/28 10:43
     */
    @ExceptionHandler({ServiceException.class})
    public ApiResult<Objects> serviceException(ServiceException e) {
        logger.error(ERROR_TITLE, e);
        return ApiResult.fail(e.getMessage());
    }

    /**
     * 无此权限，500
     * @param e 错误信息
     * @return ApiResult
     * @author haigle
     * @date 2018/11/28 10:43
     */
    @ExceptionHandler({NotPermissionException.class})
    public ApiResult<Objects> serviceException(NotPermissionException e) {
        logger.error(ERROR_TITLE, e);
        return ApiResult.code(NOT_PERMISSION_EXPIRED.getValue(), NOT_PERMISSION_EXPIRED.getName());
    }

    /**
     * 服务器异常，500
     * @param e 错误信息
     * @return ApiResult
     * @author haigle
     * @date 2018/11/28 10:43
     */
//    @ExceptionHandler({RuntimeException.class})
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ApiResult noPermissionException(RuntimeException e) {
//        logger.error(ERROR_TITLE, e);
//        return ApiResult.fail("各种错误，你就找吧");
//    }

}
