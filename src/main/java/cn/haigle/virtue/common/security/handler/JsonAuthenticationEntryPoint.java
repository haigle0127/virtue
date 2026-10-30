package cn.haigle.virtue.common.security.handler;

import cn.haigle.virtue.common.exception.CodeConstants;
import cn.haigle.virtue.common.pojo.ApiResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 未登录或 token 无效时返回 401 JSON
 *
 * @author haigle
 */
@Component
public class JsonAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Resource
    private JsonMapper jsonMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        jsonMapper.writeValue(response.getOutputStream(),
                ApiResult.code(CodeConstants.TOKEN_ERROR.getValue(), CodeConstants.TOKEN_ERROR.getName()));
    }

}
