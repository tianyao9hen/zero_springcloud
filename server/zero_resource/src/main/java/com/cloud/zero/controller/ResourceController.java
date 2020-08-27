package com.cloud.zero.controller;

import com.cloud.zero.entities.auth.SimpleUserEntity;
import com.cloud.zero.utils.UserUtils;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * ResourceController
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-08-26
 */
@RestController()
@RequestMapping("/res")
public class ResourceController {

    @RequestMapping("/get")
    public SimpleUserEntity getInfo(HttpServletRequest request){
        try {
            SimpleUserEntity nowUser = UserUtils.getNowUser(request);
            return nowUser;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}