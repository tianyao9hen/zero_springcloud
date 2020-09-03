package com.cloud.zero.controller;

import com.cloud.zero.constant.BaseConstant;
import com.cloud.zero.entities.AuthUserEntity;
import com.cloud.zero.entities.auth.SimpleUserEntity;
import com.cloud.zero.entities.common.ResultContent;
import com.cloud.zero.enumType.FwWebError;
import com.cloud.zero.service.JwtAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
     * @param user 登陆信息
     * @Return com.cloud.zero.entities.common.ResultConstant
     */
    @PostMapping("/login")
    public ResultContent login(@RequestBody AuthUserEntity user){
        ResultContent resultConstant = new ResultContent();
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
    public ResultContent refresh(@RequestHeader(BaseConstant.JWT_HEADER_NAME) String token){
        ResultContent resultConstant = new ResultContent();
        try {
            resultConstant.setResult(jwtAuthService.refreshToken(token));
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            resultConstant.setError(e);
        }
        return resultConstant;
    }

    /**
     * @Description 返回检查鉴权的结果，到这里其实已经鉴权完成了
     * @param token
     * @param checkUrl
     * @Return com.cloud.zero.entities.auth.SimpleUserEntity
     */
    @PostMapping("/checkUser")
    public SimpleUserEntity checkUser(@RequestParam("token") String token,
                                      @RequestParam("checkUrl") String checkUrl,
                                    HttpServletRequest request){
        AuthUserEntity nowUser = (AuthUserEntity) request.getAttribute("nowUser");
        nowUser.setPassword("");
        SimpleUserEntity userEntity = nowUser.packageSimpleUser();
        return userEntity;
    }

}