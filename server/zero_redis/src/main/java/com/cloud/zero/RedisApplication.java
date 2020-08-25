package com.cloud.zero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * RedisApplication
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-08-25
 */
//@SpringBootApplication
public class RedisApplication {

    //@Autowired
    private RedisTemplate redisTemplate;

    public static void main(String[] args){
        SpringApplication.run(RedisApplication.class,args);
    }
}