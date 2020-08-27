package com.cloud.zero.service;

import com.cloud.zero.entities.auth.SimpleUserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * LoginCheckApi
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-08-26
 */
@Component
@FeignClient("ZERO-AUTH") //feign调用的服务注册到注册中心的名称
@RequestMapping("/auth")
public interface LoginCheckApi {

    @PostMapping("/checkUser")
    public SimpleUserEntity checkUser(@RequestParam("token") String token, @RequestParam("checkUrl") String checkUrl);
}