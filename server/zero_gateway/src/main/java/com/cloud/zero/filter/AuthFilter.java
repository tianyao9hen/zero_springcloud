package com.cloud.zero.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.zero.constant.BaseConstant;
import com.cloud.zero.entities.auth.SimpleUserEntity;
import com.cloud.zero.entities.common.ResultContent;
import com.cloud.zero.enumType.FwWebError;
import com.cloud.zero.service.LoginCheckApi;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/**
 * AuthFilter
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-08-26
 */
@Component
@Slf4j
public class AuthFilter implements GlobalFilter,Ordered {

    @Autowired
    private LoginCheckApi loginCheckApi;

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

        //获取header中的token
        HttpHeaders headers = request.getHeaders();
        List<String> tokenList = headers.get(BaseConstant.JWT_HEADER_NAME);
        if(tokenList==null || tokenList.size()<=0){
            //没有token认为没有登陆
            DataBuffer dataBuffer = setResponse(FwWebError.NO_LOGIN, response);
            return response.writeWith(Mono.just(dataBuffer));
        }

        //通过token得到userEntity对象
        String token = tokenList.get(0);
        //这里选择使用userEntity作为返回值，原因是因为，使用通用的ResultContant因为包含了Object类型的属性无法json序列化
        //如果返回userEntity则认为用户已登陆并且存在该权限，如果没有返回，则用户未登陆或没有权限，直接跳转到登陆页面
        SimpleUserEntity userEntity = null;
        try{
            userEntity = loginCheckApi.checkUser(token, path);
        }catch(Exception e){
            //鉴权失败
            log.error(e.getMessage(),e);
            DataBuffer dataBuffer = setResponse(FwWebError.NO_PERMISSION, response);
            return response.writeWith(Mono.just(dataBuffer));
        }
        if(userEntity == null || userEntity.getId() == null){
            //鉴权失败
            DataBuffer dataBuffer = setResponse(FwWebError.REFRESH_LOGIN, response);
            return response.writeWith(Mono.just(dataBuffer));
        }

        //将用户对象转换成json格式保存到请求头中，转发到其他服务
        String json = JSONObject.toJSONString(userEntity);
        try {
            json = URLEncoder.encode(json, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String finalJson = json;
        Consumer<HttpHeaders> httpHeaders = httpHeader -> {
            httpHeader.set("nowUser", finalJson);
        };
        ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate().headers(httpHeaders).build();
        exchange.mutate().request(serverHttpRequest).build();

        //将当前的token保存再响应头中
        String nowToken = userEntity.getToken();
        //增加返回响应头，以及Access-Control-Expose-Headers响应头，作用是让我们自定义的响应头能够被浏览器读取
        exchange.getResponse().getHeaders().set(BaseConstant.JWT_HEADER_NAME,nowToken);
        exchange.getResponse().getHeaders().set("Access-Control-Expose-Headers",BaseConstant.JWT_HEADER_NAME);

        //通过过滤器
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    /**
     *@Description 当鉴权失败时设置响应对象，响应鉴权失败
     *@Param
     *@Return
     */
    private DataBuffer setResponse(FwWebError fwWebError,ServerHttpResponse response){

        response.getHeaders().set("Content-Type","application/json;charset=UTF-8");

        ResultContent resultConstant= new ResultContent();
        resultConstant.setError(fwWebError);
        Object jsonObject = JSON.toJSON(resultConstant);
        String json = JSONObject.toJSONString(jsonObject);
        //String json = JSONObject.toJSONString(resultConstant);
        DataBuffer buffer = response.bufferFactory().wrap(json.getBytes());
        return buffer;
    }

}