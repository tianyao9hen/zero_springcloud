package com.cloud.zero.controller;

import com.cloud.zero.entities.AuthUserEntity;
import com.cloud.zero.entities.common.ResultConstant;
import com.cloud.zero.enumType.FwWebError;
import com.cloud.zero.service.JwtAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * UserEntityController
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-08-26
 */
@RestController
@RequestMapping("/auth")
@Slf4j
public class UserEntityController {

    @Autowired
    private JwtAuthService jwtAuthService;

    /**
     * @Description 登陆
     * @param
     * @Return com.cloud.zero.entities.common.ResultConstant
     */
    @PostMapping("/login")
    public ResultConstant login(@RequestBody AuthUserEntity user){
        ResultConstant resultConstant = new ResultConstant();
        String username = user.getUsername();
        String password = user.getPassword();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            resultConstant.setError(FwWebError.EMPTY_LOGINNAME_OR_LOGINPASSWORD);
            return resultConstant;
        }
        try{
            AuthUserEntity userEntity = jwtAuthService.login(username, password);
            resultConstant.setResult(userEntity.packageSimpleUser());
        }catch(Exception e){
            log.error(e.getMessage(),e);
            resultConstant.setError(e);
        }
        return resultConstant;
    }

    @PostMapping("/refreshtoken")
    public ResultConstant refresh(@RequestHeader("${jwt.header}") String token){
        ResultConstant resultConstant = new ResultConstant();
        try {
            resultConstant.setResult(jwtAuthService.refreshToken(token));
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            resultConstant.setError(e);
        }
        return resultConstant;
    }

}