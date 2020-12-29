package cn.haigle.around.common.util;

import cn.haigle.around.common.interceptor.exception.TokenExpiredException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

/**
 * jwt生成
 * @author haigle
 * @date 2019/6/19 15:15
 */
public class JwtUtils {


    /**
     * 过期时间
     */
    private static final Long EXPIREDTIME = 3600 * 24 * 7 * 1000L;

    /**
     * 发行人（创建人）
     */
    private static final String ISSUER = "haigle567";

    /**
     * 创建token字符串
     * @param uid String
     * @return java.lang.String
     * @author haigle
     * @date 2018/7/24 9:45
     */
    public static String sign(String uid) {
        long nowMillis = System.currentTimeMillis();

        Date now = new Date(nowMillis);
        Date expireTime = new Date(nowMillis + EXPIREDTIME);
        // 创建token时间
        JwtBuilder builder = Jwts.builder().setSubject(uid).setIssuedAt(now)
                // 过期时间
                .setExpiration(expireTime)
                // 发行者（可以理解为创建人）
                .setIssuer(ISSUER)
                .signWith(SignatureAlgorithm.HS256, getKeyInstance());

        return builder.compact();
    }


    /**
     * 使用HS256签名算法和生成的signingKey最终的Token,claims中是有效载荷
     * @return java.security.Key
     * @author haigle
     * @date 2018/7/24 9:45
     */
    private static Key getKeyInstance() {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.PS256;
        //使用ISSUER
        byte[] apiKeySecretBytes = Base64.getEncoder().encode(ISSUER.getBytes());

        return new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    }

    /**
     * 解析token获取用户信息
     * @param token	 需要解析的token
     * @author haigle
     * @date 2018/7/25 9:21
     */
    public static Long getSubject(String token) {
        try {
            String uid = Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(token).getBody().getSubject();
            return Long.valueOf(uid);
        } catch (Exception e) {
            throw new TokenExpiredException();
        }
    }

    public static void main(String[] args) {
        System.out.println(sign("715841129097990144"));
    }
}
