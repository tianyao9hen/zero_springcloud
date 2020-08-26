package com.cloud.zero.service;

import com.cloud.zero.entities.AuthUserEntity;

/**
 * jwtAuthService
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-08-26
 */
public interface JwtAuthService {

    AuthUserEntity login(String username,String password) throws Exception;

    String refreshToken(String oldToken) throws Exception;
}