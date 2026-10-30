package cn.haigle.virtue.common.security.handler;

import cn.haigle.virtue.common.exception.CodeConstants;
import cn.haigle.virtue.common.pojo.ApiResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 已登录但权限不足时返回 403 JSON
 *
 * @author haigle
 */
@Component
public class JsonAccessDeniedHandler implements AccessDeniedHandler {

    @Resource
    private JsonMapper jsonMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        jsonMapper.writeValue(response.getOutputStream(),
                ApiResult.code(CodeConstants.NOT_PERMISSION_EXPIRED.getValue(),
                        CodeConstants.NOT_PERMISSION_EXPIRED.getName()));
    }

}
