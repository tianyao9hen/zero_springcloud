package com.cloud.zero.service;

import com.cloud.zero.entities.AuthUserEntity;
import com.cloud.zero.entities.auth.SimpleUserEntity;

/**
 * jwtAuthService
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-08-26
 */
public interface UserEntityService {

    AuthUserEntity login(String username,String password) throws Exception;

    Integer logout(SimpleUserEntity userEntity);

    String refreshToken(String oldToken) throws Exception;

    String refreshTokenByDuration(String oldToken) throws Exception;
}