package com.cloud.zero.controller;

import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/get")
    public String getInfo(){
        return "123";
    }

}