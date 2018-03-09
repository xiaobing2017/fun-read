package com.bing.funread.common.utils;

import com.bing.funread.common.constants.CommonConstant;
import com.bing.funread.common.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * Description:token处理工具类
 * Author: zhangfusheng
 * Date: 2018/3/7 下午4:43
 */
public class TokenUtil {

    private static final Logger logger = LoggerFactory.getLogger(TokenUtil.class);

    private static final String SECRET = "xiaobing-funread";

    public static String createToken(User user) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Key signingKey = new SecretKeySpec(DatatypeConverter.parseBase64Binary(SECRET),
                signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .claim(CommonConstant.TOKEN_USER_ID, user.getId())
                .claim(CommonConstant.TOKEN_UNION_ID, user.getUnionId())
                .setSubject(user.getUnionId())
                .setIssuer("fun-read-server")
                .setIssuedAt(new Date()) // 设置签发时间
//                .setExpiration(expiration) // 设置过期时间
                .signWith(signatureAlgorithm, signingKey);
        return builder.compact();
    }

    public static Claims parser(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                    .parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            logger.error("TokenUtil-parser token过期 ：{}.", e);
            throw e;
        } catch(Exception e) {
            logger.error("TokenUtil-parser Invalid token,原因：{}.", e);
            return null;
        }
    }
}
