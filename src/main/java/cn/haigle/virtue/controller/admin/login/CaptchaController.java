package cn.haigle.virtue.controller.admin.login;

import cn.haigle.virtue.common.pojo.ApiResult;
import cn.haigle.virtue.common.web.Constant;
import cn.haigle.virtue.controller.admin.login.vo.CaptchaRespVO;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.util.IdUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * 验证码管理
 * @author haigle
 * @date 2019/6/3 14:18
 */
@Tag(name = "验证码")
@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    private static final String CAPTCHA_CODE = "virtue-login-captcha:";

    @Autowired
    public RedisTemplate<String, String> redisTemplate;

    /**
     * 获取验证码
     * <p>生成图形验证码并返回验证码标识和图片Base64数据</p>
     *
     * @return 验证码响应对象，包含验证码标识(mark)和验证码图片(img)
     * @author haigle
     * @date 2024/12/10 13:55
     */
    @Operation(summary = "获取验证码")
    @GetMapping("/captchaImage")
    public ApiResult<CaptchaRespVO> captchaImage() {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(150, 60, 4, 20);
        lineCaptcha.createCode();
        String mark = IdUtil.simpleUUID();
        redisTemplate.opsForValue().set(CAPTCHA_CODE+mark, lineCaptcha.getCode(), 2, TimeUnit.MINUTES);
        CaptchaRespVO captchaRespVO = new CaptchaRespVO();
        captchaRespVO.setMark(mark);
        captchaRespVO.setImg(lineCaptcha.getImageBase64());
        return ApiResult.ok(captchaRespVO);
    }
}
