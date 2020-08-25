package com.cloud.zero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * GatewayApplication
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-08-25
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class GatewayApplication {
    public static void main(String[] args){
        SpringApplication.run(GatewayApplication.class,args);
    }
}