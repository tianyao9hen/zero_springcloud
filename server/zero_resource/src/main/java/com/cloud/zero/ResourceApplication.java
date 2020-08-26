package com.cloud.zero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * ResourceApplication
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-08-26
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ResourceApplication {

    public static void main(String[] args){
        SpringApplication.run(ResourceApplication.class,args);
    }
}