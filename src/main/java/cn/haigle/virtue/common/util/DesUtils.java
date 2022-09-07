package cn.haigle.virtue.common.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * des3加密工具
 * @author haigle
 * @date 2018/11/14 16:42
 */
public class DesUtils {


    /**
     * 加密
     * @param src 待加密字符串
     * @param desKey 解密字符串
     * @return String
     */
    public static String encrypt(String src, String desKey) {
        String result = null;
        try {
            SecureRandom secureRandom = new SecureRandom();
            DESedeKeySpec sedeKeySpec = new DESedeKeySpec(desKey.getBytes(Charset.forName("UTF-8")));

            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey key = secretKeyFactory.generateSecret(sedeKeySpec);

            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, secureRandom);
            byte[] bytes = Base64.getEncoder().encode(cipher.doFinal(src.getBytes(Charset.forName("UTF-8"))));
            result = new String(bytes, Charset.forName("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 3重DES解密
     * @param src 待解密字符串
     * @param desKey 解密字符串
     * @return String
     */
    public static String decrypt(String src, String desKey) {
        String deresult = null;
        try {
            SecureRandom secureRandom = new SecureRandom();
            DESedeKeySpec sedeKeySpec = new DESedeKeySpec(desKey.getBytes(Charset.forName("UTF-8")));

            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey key = secretKeyFactory.generateSecret(sedeKeySpec);

            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, secureRandom);

            byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(src));
            deresult = new String(bytes, Charset.forName("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deresult;
    }

    public static void main(String[] args) {
        String key = StringUtils.getRandomStr(512);
//        System.out.println(IdUtils.getId());
        System.out.println(key);
        System.out.println(encrypt("admin", key));
//        System.out.println(decrypt("ulNuPT2JeYc=","WGP6ThjqpCA1vQlIeLXt0OVi"));
    }

}
