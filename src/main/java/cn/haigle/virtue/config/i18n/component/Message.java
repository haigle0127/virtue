package cn.haigle.virtue.config.i18n.component;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * 消息基类
 * @author haigle
 * @date 2019/7/29 15:59
 */
public class Message {

    @Resource(name = "messageSource")
    private MessageSource source;

    private static Object object = null;

    /**
     * 抛出校验错误异常
     * @param key 获取提示键值
     * @param args 不知道啥用
     */
    public String message(String key, Object... args) {
        // 消息的参数化和国际化配置
        Locale locale = LocaleContextHolder.getLocale();
        return source.getMessage(key, args, locale);
    }

    /**
     * 抛出校验错误异常
     * @param key 获取提示键值
     * @author haigle
     * @date 2018/7/25 10:57
     */
    public String message(String key) {
        return message(key, object);
    }
}
