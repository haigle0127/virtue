package cn.haigle.around.common.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * sha1 加密方法
 * @author haigle
 * @date 2018/11/14 15:16
 */
public class Sha1Utils {

	/**
	 * 将字节数组转换成16进制字符串
	 * @param b byte[]
	 * @return String
	 * @author haigle
	 * @date 2018/11/14 15:18
	 */
	private static String byte2hex(byte[] b) {
        StringBuilder sbDes = new StringBuilder();
        String tmp;
        for (byte aB : b) {
            tmp = (Integer.toHexString(aB & 0xFF));
            if (tmp.length() == 1) {
                sbDes.append("0");
            }
            sbDes.append(tmp);
        }
        return sbDes.toString();
    }

    /**
     * 加密
     * @param strSrc String
     * @return String
     * @author haigle
     * @date 2018/11/14 15:19
     */
    public static String encrypt(String strSrc) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            String strDes;
            byte[] bt = strSrc.getBytes(StandardCharsets.UTF_8);
            digest.update(bt);
            strDes = byte2hex(digest.digest());
            return strDes;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
