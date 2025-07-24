package com.darrenchan.admin.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

/**
 * Json Web Token工具类
 * 非对称加密
 *
 * 用户登录时，后端会根据用户信息来生成一个JWT令牌，并将其返回给前端。
 * 前端会将JWT令牌存储在本地（如localStorage或sessionStorage或者Cookie）中，并在后续的请求中将其附加到请求头中。
 * 那么前端的后续请求中就会携带这个JWT令牌，后端会对其进行验证，无需重复登录。
 */
public class JwtHelper {
    //令牌的过期时间
    private static long tokenExpiration = 365L * 24 * 60 * 60 * 1000;
    //加密的key
    private static Key key = Keys.hmacShaKeyFor("abcdefghijklmnopqrstuvwxyzasdfasdfsadfasdfasdf".getBytes());

    public static String createToken(Long userId, String userName) {
        return Jwts.builder()
                .setSubject("P2P-USER")
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .claim("userId", userId)
                .claim("userName", userName)
                .signWith(key)
                .compact();
    }

    public static Long getUserId(String token) {
        try {
            if (token == null) return null;

            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            String userId = claims.get("userId").toString();
            return Long.parseLong(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getUserName(String token) {
        try {
            if (token == null) return null;

            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            return (String) claims.get("userName");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String token = JwtHelper.createToken(1L, "baiyuan");//"eyJhbGciOiJIUzUxMiIsInppcCI6IkdaSVAifQ.H4sIAAAAAAAAAKtWKi5NUrJSCjAK0A0Ndg1S0lFKrShQsjI0MzY2sDQ3MTbQUSotTi3yTFGyMjKEsP0Sc1OBWp6unfB0f7NSLQDxzD8_QwAAAA.2eCJdsJXOYaWFmPTJc8gl1YHTRl9DAeEJprKZn4IgJP9Fzo5fLddOQn1Iv2C25qMpwHQkPIGukTQtskWsNrnhQ";//JwtHelper.createToken(7L, "admin");
        System.out.println(token);
        System.out.println(JwtHelper.getUserId(token));
        System.out.println(JwtHelper.getUserName(token));
    }
}
