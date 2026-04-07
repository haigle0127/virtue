package cn.haigle.virtue.controller.admin.login;

import cn.haigle.virtue.common.pojo.ApiResult;
import cn.haigle.virtue.common.web.Constant;
import cn.haigle.virtue.controller.admin.login.vo.CaptchaRespVO;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * 验证码管理
 * @author haigle
 * @date 2019/6/3 14:18
 */
@RestController
@RequestMapping(Constant.API)
public class CaptchaController {

    private static final String CAPTCHA_CODE = "virtue-login-captcha:";

    @Autowired
    public RedisTemplate<String, String> redisTemplate;

    /**
     * 获取验证码
     * @author haigle
     * @date 2024/12/10 13:55
     */
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
