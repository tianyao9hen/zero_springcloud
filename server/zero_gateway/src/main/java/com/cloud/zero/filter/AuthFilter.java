package com.cloud.zero.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Set;

/**
 * AuthFilter
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-08-26
 */
//@Component
@Slf4j
public class AuthFilter implements GlobalFilter,Ordered {

    @Value("${whitelist.urlset}")
    private Set<String> urlSet;

    @Value("${whitelist.fileset}")
    private Set<String> fileSet;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String path = request.getURI().getPath();

        //如果是option类型的请求直接放行
        if(request.getMethod().equals(RequestMethod.OPTIONS.name())){
            chain.filter(exchange);
        }
        //如果请求包含在白名单中直接放行
        if(path.lastIndexOf("/") >= 0){
            if(urlSet.contains(path)){
                return chain.filter(exchange);
            }
        }
        //如果请求的是白名单中规定格式的静态文件，直接放行
        if(path.lastIndexOf(".") > 0){
            String end = path.substring(path.lastIndexOf(".") + 1);
            if(fileSet.contains(end)){
                return chain.filter(exchange);
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}