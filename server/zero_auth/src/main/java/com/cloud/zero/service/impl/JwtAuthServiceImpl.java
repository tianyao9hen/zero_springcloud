package com.cloud.zero.service.impl;

import com.cloud.zero.constant.BaseConstant;
import com.cloud.zero.entities.AuthUserEntity;
import com.cloud.zero.enumType.FwWebError;
import com.cloud.zero.exception.ServiceReturnException;
import com.cloud.zero.service.AuthService;
import com.cloud.zero.service.JwtAuthService;
import com.cloud.zero.utils.JwtUtils;
import com.cloud.zero.utils.RedisUtil;
import com.cloud.zero.utils.RsaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * JwtAuthServiceImpl
 * 涉及到生成和更新jwt token的权限Service
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-08-26
 */
@Service
public class JwtAuthServiceImpl implements JwtAuthService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthService authRedisService;

    /**
     * @Description 生成jwt的登陆方法
     * @Param username
     * @Param password
     * @Return com.cloud.zero.entities.AuthUserEntity
     */
    @Override
    public AuthUserEntity login(String username, String password) throws Exception {
        try{
            //根据用户名密码自己创建了UsernamePasswordAuthenticationToken，这个token原本是在usernamePasswordFilter中生成并进行登陆验证的。
            UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username,password);
            Authentication authentication = authenticationManager.authenticate(upToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        catch(Exception e){
            //如果验证不通过，则抛出异常
            throw new ServiceReturnException(FwWebError.WRONG_LOGNNAME_OR_LOGINPASSWORD);
        }
        //生成jwt
        AuthUserEntity userEntity = userDetailsService.loadUserByUsername(username);
        AuthUserEntity tokenUserEntity = new AuthUserEntity();
        tokenUserEntity.setUsername(userEntity.getUsername());
        tokenUserEntity.setId(userEntity.getId());
        PrivateKey privateKey = RsaUtils.getPrivateKey(BaseConstant.PRI_KEY_PATH);
        String token = JwtUtils.generateToken(tokenUserEntity, privateKey,BaseConstant.LOGIN_JWT_TIMEOUT_MINUTE.intValue());

        //返回user数据
        userEntity.setToken(token);
        userEntity.setPassword("");

        //将用户信息保存到redis
        String result = authRedisService.login(userEntity.packageSimpleUser());
        return userEntity;
    }


    /**
     * @Description 刷新token
     * @Param oldToken
     * @Return java.lang.String
     */
    @Override
    public String refreshToken(String oldToken) throws Exception {
        PublicKey publicKey = RsaUtils.getPublicKey(BaseConstant.PUB_KEY_PATH);
        PrivateKey privateKey = RsaUtils.getPrivateKey(BaseConstant.PRI_KEY_PATH);
        if(!JwtUtils.isTokenExpired(oldToken,publicKey)){
            return JwtUtils.refreshToken(oldToken, publicKey, privateKey, AuthUserEntity.class, BaseConstant.LOGIN_JWT_TIMEOUT_MINUTE.intValue());
        }
        return null;
    }
}