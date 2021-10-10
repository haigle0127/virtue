package cn.haigle.virtue.common.interceptor.advice;

import cn.haigle.virtue.common.interceptor.exception.*;
import cn.haigle.virtue.common.interceptor.model.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * 通用校验放回校验
 * @author haigle
 * @date 2018/11/12 13:10
 */
@RestControllerAdvice
public class ControllerValidateAdvice {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.profiles.active}")
    private String profile;

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
    public ApiResult bindException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        return ApiResult.code(901, Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
    }

    /**
     * 请求没有携带token，提示登录，401
     * @return ApiResult
     * @author haigle
     * @date 2018/11/28 10:39
     */
    @ExceptionHandler(NoTokenException.class)
    public ApiResult noTokenException() {
        return ApiResult.code(401, "验证错误");
    }

    /**
     * token过期或者不是正确的token，402
     * @return ApiResult
     * @author haigle
     * @date 2018/11/28 10:39
     */
    @ExceptionHandler(TokenExpiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResult tokenException() {
        return ApiResult.code(402, "登录过期, 请重新登录");
    }

    /**
     * 没有权限访问，403
     * 更改权限规则时，看是否必要加上参数 HttpServletResponse httpServletResponse, 并：
     * // httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
     * // httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
     * @return ApiResult
     * @author haigle
     * @date 2018/11/28 10:40
     */
    @ExceptionHandler(NoPermissionAccessException.class)
    public ApiResult noPermissionException(NoPermissionAccessException e) {
        logger.error(ERROR_TITLE, e);
        return ApiResult.code(403, "无权限访问");
    }

    /**
     * 上传文件失败，521
     * @param e 错误信息
     * @return ApiResult
     * @author haigle
     * @date 2018/11/28 14:41
     */
    @ExceptionHandler(UploadFileException.class)
    public ApiResult uploadFileException(UploadFileException e) {
        logger.error(ERROR_TITLE, e);
        return ApiResult.code(521, "上传失败");
    }

    /**
     * redis数据库错误 501
     * @param e 错误信息
     * @return ApiResult
     * @author haigle
     * @date 2018/11/28 14:39
     */
    @ExceptionHandler(RedisException.class)
    public ApiResult redisException(RedisException e) {
        logger.error(ERROR_TITLE, e);
        return ApiResult.code(501, "Redis数据库错误");
    }

    /**
     * redis数据库错误(未连接) 501
     * @param e 错误信息
     * @return ApiResult
     * @author haigle
     * @date 2018/11/28 14:39
     */
    @ExceptionHandler(RedisConnectionFailureException.class)
    public ApiResult redisException(RedisConnectionFailureException e) {
        logger.error(ERROR_TITLE, e);
        return ApiResult.code(501, "Redis数据库连接错误");
    }

    /**
     * 服务器异常，500
     * @param e 错误信息
     * @return ApiResult
     * @author haigle
     * @date 2018/11/28 10:43
     */
    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult noPermissionException(RuntimeException e) {

        /*
         * 其他异常
         * 开发环境返回错误信息
         */
        if (profile.equalsIgnoreCase(DEV)) {
            e.printStackTrace();
            String message = e.getMessage() == null ? "请看控制台的错误提示哟" : e.getMessage();
            return ApiResult.fail("各种错误，你就找吧");
        }
        logger.error(ERROR_TITLE, e);
        return ApiResult.fail("各种错误，你就找吧");

    }

}
