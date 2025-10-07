package org.emilia.tienchin.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.Keys;
import org.emilia.tienchin.pojo.model.LoginUser;
import org.emilia.tienchin.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static io.jsonwebtoken.Claims.EXPIRATION;
import static io.jsonwebtoken.Claims.ISSUED_AT;

@Component
public class JwtUtils {
    //    @Value("${jwt.secret}")
//    private static String secret;
    private static String secret = "dhaehakd98432684hiuhiuahd";
    private static long expiration = 24 * 60 * 60;
//
//    @Value("${jwt.expiration}")
//    private static long expiration;

    /**
     * 从token中获取用户名
     */
    public static String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * 从token中获取创建时间
     */
    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    /**
     * 从token中获取过期时间
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     *
     * 提取声明
     */
    public static <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * 从token中获取所有声明
     */
    private static Claims getAllClaimsFromToken(String token) {
        byte[] bytes = (byte[]) Decoders.BASE64.decode(secret);
        SecretKey secretKey = Keys.hmacShaKeyFor(bytes);
        JwtParserBuilder jwtParserBuilder = Jwts.parser().verifyWith(secretKey);
        return jwtParserBuilder.build().parseSignedClaims(token).getPayload();
    }

    /**
     * 检查token是否过期
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 忽略token过期检查
     */
    private Boolean ignoreTokenExpiration(String token) {
        return false;
    }

    /**
     * 生成token
     */
    public static String generateToken(LoginUser loginUser) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", loginUser.getUserId());
        return doGenerateToken(claims, loginUser.getUsername());
    }

    /**
     * 生成token的具体实现
     */
    private static String doGenerateToken(Map<String, Object> claims, String subject) {
        final Date createdDate = new Date();
        final Date expirationDate = calculateExpirationDate(createdDate);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 计算token过期时间
     */
    private static Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + expiration * 1000);
    }

    /**
     * 刷新token
     */
    public String refreshToken(String token) {
        final Date createdDate = new Date();
        final Date expirationDate = calculateExpirationDate(createdDate);

        DefaultClaims claims = (DefaultClaims) getAllClaimsFromToken(token);
        claims.replace(ISSUED_AT, createdDate);
        claims.replace(EXPIRATION, expirationDate);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 验证token
     */
    public Boolean validateToken(String token, String username) {
        final String usernameFromToken = getUsernameFromToken(token);
        return (usernameFromToken.equals(username) && !isTokenExpired(token) && !ignoreTokenExpiration(token));
    }
}
