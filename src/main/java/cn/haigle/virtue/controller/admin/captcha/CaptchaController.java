package cn.haigle.virtue.controller.admin.captcha;

import cn.haigle.virtue.common.pojo.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * 验证码管理
 *
 * @author haigle
 */
@Tag(name = "验证码")
@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    private static final String CAPTCHA_CODE = "virtue-login-captcha:";

    @Autowired
    public RedisTemplate<String, String> redisTemplate;

    // @Operation(summary = "获取验证码")
    // @PostMapping("/get")
    // public ApiResult<CaptchaRespVO> getCaptcha() {
    //     LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(150, 60, 4, 20);
    //     lineCaptcha.createCode();
    //     String mark = IdUtil.simpleUUID();
    //     redisTemplate.opsForValue().set(CAPTCHA_CODE + mark, lineCaptcha.getCode(), 2, TimeUnit.MINUTES);
    //     CaptchaRespVO captchaRespVO = new CaptchaRespVO();
    //     captchaRespVO.setMark(mark);
    //     captchaRespVO.setImg(lineCaptcha.getImageBase64());
    //     return ApiResult.ok(captchaRespVO);
    // }

    @Operation(summary = "校验验证码")
    @PostMapping("/check")
    public ApiResult<Boolean> checkCaptcha(@RequestBody Map<String, String> params) {
        String mark = params.get("mark");
        String code = params.get("code");
        if (mark == null || code == null) {
            return ApiResult.fail("验证码参数不完整");
        }
        String cachedCode = redisTemplate.opsForValue().get(CAPTCHA_CODE + mark);
        if (cachedCode == null) {
            return ApiResult.fail("验证码已过期");
        }
        if (!cachedCode.equalsIgnoreCase(code)) {
            return ApiResult.fail("验证码不正确");
        }
        // 验证成功后删除
        redisTemplate.delete(CAPTCHA_CODE + mark);
        return ApiResult.ok();
    }

}
