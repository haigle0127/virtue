package cn.haigle.virtue.common.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.UnsupportedEncodingException;

/**
 * 字符串工具
 * @author haigle
 * @date 2018/5/22 14:50
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    private static final char SEPARATOR = '_';
    private static final String CHARSET_NAME = "UTF-8";

    private final static String CHARS = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890";
    private final static String NUM = "1234567890";

    private StringUtils() {
    }

    /**
     * 获取随机数
     * @param length int
     * @return Long
     * @author haigle
     * @date 2018/11/14 16:45
     */
    public static Long getRandomNumber(int length) {
        return Long.valueOf(getRandomNumberStr(length));
    }

    /**
     * 获取随机数
     * @param length int
     * @return String
     * @author haigle
     * @date 2018/11/14 16:45
     */
    public static String getRandomNumberStr(int length) {
        return RandomStringUtils.random(length, NUM);
    }

    /**
     * 获取随机字符串
     * @param length int
     * @return  String
     * @author haigle
     * @date 2018/11/14 16:44
     */
    public static String getRandomStr(int length) {
        return RandomStringUtils.random(length, CHARS);
    }

    /**
     * 转换为字节数组
     * @param str String
     * @return byte[]
     * @author haigle
     * @date 2018/3/29 13:58
     */
    public static byte[] getBytes(String str) {
        if (str != null) {
            try {
                return str.getBytes(CHARSET_NAME);
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        } else {
            return null;
        }
    }
}
