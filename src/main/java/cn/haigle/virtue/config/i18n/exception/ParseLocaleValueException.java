package cn.haigle.virtue.config.i18n.exception;

/**
 * 解析语言失败异常
 * @author haigle
 * @date 2019/7/29 16:03
 */
public class ParseLocaleValueException extends RuntimeException {
    public ParseLocaleValueException(String message) {
        super(message);
    }
}
