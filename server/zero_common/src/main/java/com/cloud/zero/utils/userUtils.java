package com.cloud.zero.utils;

import com.alibaba.fastjson.JSONObject;
import com.cloud.zero.entities.auth.SimpleUserEntity;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * userUtils
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-08-27
 */
public class UserUtils {

    /*public static SimpleUserEntity getNowUser(HttpServletRequest request) throws UnsupportedEncodingException {
        String userEntityJson = request.getHeader("nowUser");
        if(userEntityJson == null){
            return null;
        }
        String decode = URLDecoder.decode(userEntityJson,"utf-8");
        SimpleUserEntity userEntity = JSONObject.parseObject(decode, SimpleUserEntity.class);
        return userEntity;
    }*/
}