package cn.haigle.virtue.common.exception;

import cn.haigle.virtue.common.pojo.ApiResult;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/**
 * 全局异常处理
 *
 * @author haigle
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * JSON 参数校验失败
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResult<Void> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse(CodeConstants.FORM_VALIDATION_ERROR.getName());
        return ApiResult.code(CodeConstants.FORM_VALIDATION_ERROR.getValue(), message);
    }

    /**
     * 单参数校验失败
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ApiResult<Void> handleConstraintViolation(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream()
                .findFirst()
                .map(violation -> violation.getMessage())
                .orElse(CodeConstants.FORM_VALIDATION_ERROR.getName());
        return ApiResult.code(CodeConstants.FORM_VALIDATION_ERROR.getValue(), message);
    }

    /**
     * 请求体缺失、JSON 格式错误或参数类型不匹配
     */
    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
    public ApiResult<Void> handleUnreadableRequest(Exception e) {
        return ApiResult.code(CodeConstants.FORM_VALIDATION_ERROR.getValue(), "请求参数格式错误");
    }

    /**
     * 业务异常，保留业务码；鉴权类错误同步 HTTP 状态码
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ApiResult<Void>> handleServiceException(ServiceException e) {
        log.error("业务异常：{}", e.getMessage());
        Integer code = e.getCode() != null ? e.getCode() : CodeConstants.FAIL.getValue();
        ApiResult<Void> body = ApiResult.code(code, e.getMessage());
        if (HttpStatus.UNAUTHORIZED.value() == code || HttpStatus.FORBIDDEN.value() == code) {
            return ResponseEntity.status(code).body(body);
        }
        return ResponseEntity.ok(body);
    }

    /**
     * 账号不存在或密码错误，统一提示，避免暴露账号是否存在
     */
    @ExceptionHandler({BadCredentialsException.class, UsernameNotFoundException.class})
    public ApiResult<Void> handleBadCredentials(AuthenticationException e) {
        return ApiResult.code(CodeConstants.USER_ERROR.getValue(), CodeConstants.USER_ERROR.getName());
    }

    /**
     * 账号停用
     */
    @ExceptionHandler(DisabledException.class)
    public ApiResult<Void> handleDisabled(DisabledException e) {
        return ApiResult.code(CodeConstants.USER_DISABLED.getValue(), CodeConstants.USER_DISABLED.getName());
    }

    /**
     * 方法级鉴权失败，保持 HTTP 403，不能退化成 200
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResult<Void>> handleAccessDenied(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ApiResult.code(CodeConstants.NOT_PERMISSION_EXPIRED.getValue(),
                        CodeConstants.NOT_PERMISSION_EXPIRED.getName()));
    }

    /**
     * 接口不存在
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResult<Void>> handleNoResourceFound(NoResourceFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResult.code(HttpStatus.NOT_FOUND.value(), "接口不存在"));
    }

    /**
     * 服务器内部异常，保持 HTTP 500
     */
    @ExceptionHandler(ServerException.class)
    public ResponseEntity<ApiResult<Void>> handleServerException(ServerException e) {
        log.error("服务器异常：{}", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResult.code(e.getCode(), e.getMessage()));
    }

    /**
     * 兜底异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResult<Void>> handleException(Exception e) {
        log.error("系统异常", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResult.code(CodeConstants.FAIL.getValue(), CodeConstants.FAIL.getName()));
    }

}
