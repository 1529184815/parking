package com.hx.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

public class JwtUtils {
 
    //密码
    private static final String secret = "66diangezanbageixioapang12oc";

       /**
     * (默认的超时时间)token存活时间，2小时
     */
    private static final long liveMills = 3600 * 2 * 1000;


    /**
     * 获取secret
     */
    public static SecretKey obtainKey() {

        //对key进行解码
        byte[] secretBytes = secret.getBytes();
        return new SecretKeySpec(secretBytes, 0, secretBytes.length, "AES");
    }

    /**
     * 获取token，使用默认的超时时间，即2个小时。
     *
     * @param sub 主题（需要加密的字符串）
     * @return token字符串
     */
    public static String createJWT(String sub) {
        return createJWT(sub, liveMills);
    }

    /**
     * 会自动对主题对象序列化（使用fastJson进行序列化）得到字符串
     *
     * @param subObj 主题对象
     * @return token字符串
     */
    public static <T> String createJWT(T subObj) {
        return createJWT(JSONObject.toJSONString(subObj), liveMills);
    }

    /**
     * 会自动对主题对象序列化（使用fastJson进行序列化）得到字符串
     *
     * @param subObj    主题对象
     * @param liveMills 失效时间，单位毫秒
     * @return token字符串
     */
    public static <T> String createJWT(T subObj, Long liveMills) {
        return createJWT(JSONObject.toJSONString(subObj), liveMills);
    }

    /**
     * @param sub       需要加密的主题
     * @param liveMills 失效时间，单位毫秒
     * @return 经过jwt加密的token字符串，失效时间即当前时间+liveMills毫秒数
     */
    public static String createJWT(String sub, Long liveMills) {
        //加密模式
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long currentTimeMillis = System.currentTimeMillis();
        Date now = new Date(currentTimeMillis);  //iat token签发时间
        SecretKey secretKey = obtainKey();
        //jti表示该token的唯一id,不推荐使用相同值|isa 下发时间
        JwtBuilder jwtBuilder = Jwts.builder().setId("jti-xp").
                setIssuedAt(now). //设置签发时间
                setSubject(sub).
                signWith(signatureAlgorithm, secretKey);////设置签名秘钥
        if (liveMills > 0) {
            long expMills = currentTimeMillis + liveMills;
            Date expDate = new Date(expMills);  //失效时间
            jwtBuilder.setExpiration(expDate);
        }
        return jwtBuilder.compact();
    }

    /**
     * 解密token，返回Claims对象
     * <p>
     * 注，若token失效，会抛出{@link ExpiredJwtException}的异常
     **/
    public static Claims parseJWT(String token) {
        SecretKey key = obtainKey();
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();  // Claims [kleɪmz] 声明
    }

    /**
     * 解析token获取到sub（主题）。
     * <p>
     * 注，若token失效，会抛出{@link ExpiredJwtException}的异常
     */
    public static String parseJWT2Sub(String token) {
        SecretKey key = obtainKey();
        Claims body = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        return body.getSubject();
    }

    /**
     * 解密token获取sub，并反序列化为对象。
     *
     * @param token 需要解密的token字符串
     * @param clazz sub反序列化的对象类型
     */
    public static <T> T parseJWT2Sub(String token, Class<T> clazz) {
        SecretKey key = obtainKey();
        Claims body = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        return JSON.parseObject(body.getSubject(), clazz);
    }

}