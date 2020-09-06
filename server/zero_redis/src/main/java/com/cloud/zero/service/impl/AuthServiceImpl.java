package com.cloud.zero.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.zero.constant.BaseConstant;
import com.cloud.zero.entities.auth.SimpleUserEntity;
import com.cloud.zero.enumType.FwWebError;
import com.cloud.zero.exception.ServiceReturnException;
import com.cloud.zero.service.AuthService;
import com.cloud.zero.utils.RedisUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Base64;
import java.util.Date;

/**
 * AuthServiceImpl
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-09-03
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private RedisUtil redisUtil;

    /**
     * @Description
     * @Param userEntity 用户信息和token信息
     * @Return java.lang.String 返回redisKey
     */
    @Override
    public String login(SimpleUserEntity userEntity) {
        if(userEntity == null || userEntity.getId() == null || StringUtils.isEmpty(userEntity.getToken())){
            throw new ServiceReturnException(FwWebError.WRONG_LOGNNAME_OR_LOGINPASSWORD);
        }
        Base64.Encoder encoder = Base64.getEncoder();
        //对userEntity的id进行加密
        String encodeId = new String(encoder.encode((userEntity.getUsername().getBytes())));
        //对userEntity进行加密
        String jsonUser = JSON.toJSONString(userEntity);
        String encodeUser = new String(encoder.encode((jsonUser.getBytes())));

        //模糊删除现在可能存在的同一用户的redis数据，保证同一个账户同一时间只能登陆一次
        redisUtil.del(BaseConstant.LOGIN_REDIS_PRE + encodeId);

        //添加时间戳和头表示组成ke
        String key = BaseConstant.LOGIN_REDIS_PRE + encodeId;

        boolean redisResult = redisUtil.set(key, encodeUser, BaseConstant.LOGIN_JWT_TIMEOUT);
        if(!redisResult){
            throw new ServiceReturnException(FwWebError.REDIS_WRONG);
        }
        //返回 由idkey表示添加成功
        return encodeId;
    }

    /**
     * @Description 退出登陆，删除redis中的值
     * @Param userEntity
     * @Return java.lang.Integer
     */
    @Override
    public Integer logout(SimpleUserEntity userEntity) {
        if(userEntity == null || StringUtils.isEmpty(userEntity.getToken()) || StringUtils.isEmpty(userEntity.getUsername())){
            throw new ServiceReturnException(FwWebError.REFRESH_LOGIN);
        }
        Base64.Decoder decoder = Base64.getDecoder();
        Base64.Encoder encoder = Base64.getEncoder();

        String encodeId = new String(encoder.encode((userEntity.getUsername().getBytes())));
        String key = BaseConstant.LOGIN_REDIS_PRE + encodeId;
        boolean isHas = redisUtil.hasKey(key);
        if(isHas){
            String data = (String) redisUtil.get(key);
            SimpleUserEntity redisUser = JSONObject.parseObject(new String(decoder.decode(data)), SimpleUserEntity.class);
            if(userEntity.getToken().equals(redisUser.getToken())){
                redisUtil.del(key);
            }
        }
        return 1;
    }

    /**
     * @Description 通过username和Token得到redis中保存的userEntity信息
     * @Param username 用户名
     * @Param token jwtToken
     * @Return com.cloud.zero.entities.auth.SimpleUserEntity
     */
    @Override
    public SimpleUserEntity loadUserByUsername(String username, String token) {
        Base64.Decoder decoder = Base64.getDecoder();
        Base64.Encoder encoder = Base64.getEncoder();

        String encodeId = new String(encoder.encode((username.getBytes())));
        String key = BaseConstant.LOGIN_REDIS_PRE + encodeId;
        boolean isHas = redisUtil.hasKey(key);
        if(isHas){
            String data = (String) redisUtil.get(key);
            SimpleUserEntity userEntity = JSONObject.parseObject(new String(decoder.decode(data)), SimpleUserEntity.class);
            if(userEntity.getToken().equals(token)) return userEntity;
        }
        return null;
    }

    /**
     * @Description 将刷新token保存到redis
     * @Param username 用户名
     * @Param newToken 新的token
     * @Return java.lang.Integer 刷新成功
     */
    @Override
    public Integer refreshToken(String username, String newToken) {
        Base64.Decoder decoder = Base64.getDecoder();
        Base64.Encoder encoder = Base64.getEncoder();

        String encodeId = new String(encoder.encode((username.getBytes())));
        String key = BaseConstant.LOGIN_REDIS_PRE + encodeId;
        boolean isHas = redisUtil.hasKey(key);
        if(isHas){
            String data = (String) redisUtil.get(key);
            SimpleUserEntity userEntity = JSONObject.parseObject(new String(decoder.decode(data)), SimpleUserEntity.class);
            userEntity.setToken(newToken);
            String login = login(userEntity);
            return 1;
        }
        return 0;
    }
}