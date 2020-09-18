package com.cloud.zero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * SystemManagerApplication
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-09-06
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SystemManagerApplication {

    public static void main(String[] args){
        SpringApplication.run(SystemManagerApplication.class,args);
    }
}