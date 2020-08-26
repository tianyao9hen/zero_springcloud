package com.cloud.zero.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.beanutils.BeanUtils;
import org.joda.time.DateTime;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

/**
 * JwtUtil
 * 使用jwt进行加密和解密操作的工具类
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-07-09
 */
public class JwtUtils {
    /**
     * 私钥加密token
     *
     * @param data          需要加密的数据（载荷内容）
     * @param expireMinutes 过期时间，单位：分钟
     * @param privateKey    私钥
     * @return
     */
    public static String generateToken(Object data, PrivateKey privateKey, int expireMinutes) throws Exception {

        //1 获得jwt构建对象
        JwtBuilder jwtBuilder = Jwts.builder();
        //2 设置数据
        if (data == null) {
            throw new RuntimeException("数据不能为空");
        }
        BeanInfo beanInfo = Introspector.getBeanInfo(data.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            // 获得属性名
            String name = propertyDescriptor.getName();
            // 获得属性值
            Object value = propertyDescriptor.getReadMethod().invoke(data);
            if (value != null) {
                jwtBuilder.claim(name, value);
            }
        }
        //3 设置过期时间
        jwtBuilder.setExpiration(DateTime.now().plusMinutes(expireMinutes).toDate());
        //4 设置加密
        jwtBuilder.signWith(SignatureAlgorithm.RS256, privateKey);
        //5 构建
        return jwtBuilder.compact();
    }

    /**
     * 私钥加密token，不设置过期时间，过期由redis控制
     *
     * @param data          需要加密的数据（载荷内容）
     * @param privateKey    私钥
     * @return
     */
    public static String generateToken(Object data, PrivateKey privateKey) throws Exception {

        //1 获得jwt构建对象
        JwtBuilder jwtBuilder = Jwts.builder();
        //2 设置数据
        if (data == null) {
            throw new RuntimeException("数据不能为空");
        }
        BeanInfo beanInfo = Introspector.getBeanInfo(data.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            // 获得属性名
            String name = propertyDescriptor.getName();
            // 获得属性值
            Object value = propertyDescriptor.getReadMethod().invoke(data);
            if (value != null) {
                jwtBuilder.claim(name, value);
            }
        }
        //3 设置过期时间
        //jwtBuilder.setExpiration(DateTime.now().plusMinutes(expireMinutes).toDate());
        //4 设置加密
        jwtBuilder.signWith(SignatureAlgorithm.RS256, privateKey);
        //5 构建
        return jwtBuilder.compact();
    }

    /**
     * 通过公钥解析token
     *
     * @param token     需要解析的数据
     * @param publicKey 公钥
     * @param beanClass 封装的JavaBean
     * @return
     * @throws Exception
     */
    public static <T> T getObjectFromToken(String token, PublicKey publicKey, Class<T> beanClass) throws Exception {
        //1 获得解析后内容
        Claims body = getClaimsFromToken(token, publicKey);
        if(body == null) return null;
        //2 将内容封装到对象JavaBean
        T bean = beanClass.newInstance();
        BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            // 获得属性名
            String name = propertyDescriptor.getName();
            // 通过属性名，获得对应解析的数据
            Object value = body.get(name);
            if (value != null) {
                // 将获得的数据封装到对应的JavaBean中
                BeanUtils.setProperty(bean, name, value);
            }
        }
        return bean;
    }

    public static Claims getClaimsFromToken(String token,PublicKey publicKey){
        Claims claims;
        try{
            claims = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token).getBody();
        }catch(Exception e){
            claims = null;
        }
        return claims;
    }

    /**
     * @Description 判断令牌是否过期
     * @param token 令牌
     * @Return java.lang.Boolean 是否过期
     */
    public static Boolean isTokenExpired(String token,PublicKey publicKey){
        Claims claims = getClaimsFromToken(token, publicKey);
        if(claims == null){
            return true;
        }
        return false;
    }

    /**
     * @Description 刷新令牌
     * @param token 原令牌
     * @Return java.lang.String 新令牌
     */
    public static <T> String refreshToken(String token, PublicKey publicKey, PrivateKey privateKey,Class<T> beanClass,int expireMinutes){
        String refreshedToken;
        try{
            T object = getObjectFromToken(token, publicKey, beanClass);
            refreshedToken = generateToken(object, privateKey, expireMinutes);
        }catch(Exception e){
            refreshedToken = null;
        }
        return refreshedToken;
    }
}