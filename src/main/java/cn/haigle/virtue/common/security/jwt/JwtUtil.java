package cn.haigle.virtue.common.security.jwt;

import cn.haigle.virtue.common.interceptor.exception.ServiceException;
import cn.haigle.virtue.common.interceptor.exception.enums.CodeConstants;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.util.Date;

/**
 * JWT 工具类
 */
@Slf4j
public class JwtUtil {

    private static final String SECRET = "CHANGE_ME_JWT_SECRET";
    private static final long EXPIRATION = 86400 * 1000; // 24小时

    /**
     * 生成 JWT Token
     *
     * @param userId 用户ID
     * @return token 字符串
     */
    public static String generateToken(Long userId) {
        try {
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(String.valueOf(userId))
                    .expirationTime(new Date(System.currentTimeMillis() + EXPIRATION))
                    .issueTime(new Date())
                    .build();

            SignedJWT signedJWT = new SignedJWT(
                    new JWSHeader(JWSAlgorithm.HS256),
                    claimsSet
            );

            signedJWT.sign(new MACSigner(SECRET.getBytes()));
            return signedJWT.serialize();
        } catch (JOSEException e) {
            log.error("JWT生成失败", e);
            throw new ServiceException("Token生成失败");
        }
    }

    /**
     * 解析 JWT Token，获取用户ID
     *
     * @param token token 字符串
     * @return 用户ID
     */
    public static Long parseToken(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(SECRET.getBytes());

            if (!signedJWT.verify(verifier)) {
                throw new ServiceException(CodeConstants.TOKEN_ERROR);
            }

            JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();
            Date expirationTime = claimsSet.getExpirationTime();
            if (expirationTime != null && expirationTime.before(new Date())) {
                throw new ServiceException(CodeConstants.TOKEN_EXPIRED);
            }

            return Long.valueOf(claimsSet.getSubject());
        } catch (ParseException | JOSEException e) {
            log.error("JWT解析失败", e);
            throw new ServiceException(CodeConstants.TOKEN_ERROR);
        }
    }
}
