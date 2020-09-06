package com.cloud.zero.service;

import com.cloud.zero.entities.auth.SimpleUserEntity;

/**
 * AuthService
 * 鉴权模块redisService
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-09-03
 */
public interface AuthService {

    String login(SimpleUserEntity userEntity);

    Integer logout(SimpleUserEntity userEntity);

    SimpleUserEntity loadUserByUsername(String username, String token);

    Integer refreshToken(String username, String newToken);
}