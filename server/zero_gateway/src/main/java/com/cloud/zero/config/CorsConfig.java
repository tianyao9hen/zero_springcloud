package com.cloud.zero.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.List;

/**
 * CorsConfig
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-08-25
 */
@Configuration
public class CorsConfig {

    @Value("${cors.origin-url}")
    private List<String> originList;

    @Value("${cors.method}")
    private List<String> methodList;

    @Bean
    public CorsWebFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsWebFilter(source);
    }

    private CorsConfiguration buildConfig(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //在生产环境上最好指定域名，以免产生跨域安全问题
        for (String url : originList) {
            corsConfiguration.addAllowedOrigin(url);
        }
        corsConfiguration.addAllowedHeader("*");
        for (String method : methodList) {
            corsConfiguration.addAllowedMethod(method);
        }
        return corsConfiguration;
    }
}