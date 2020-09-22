package com.cloud.zero.filter;

import com.cloud.zero.constant.BaseConstant;
import com.cloud.zero.entities.AuthUserEntity;
import com.cloud.zero.entities.auth.SimpleUserEntity;
import com.cloud.zero.service.AuthService;
import com.cloud.zero.utils.JwtUtils;
import com.cloud.zero.utils.RsaUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.PublicKey;

/**
 * JwtAuthenticationTokenFilter
 *  接口访问鉴权过滤器
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-08-27
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter{

    @Autowired
    AuthService authRedisService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = request.getParameter("token");
            PublicKey publicKey = RsaUtils.getPublicKey(BaseConstant.PUB_KEY_PATH);
            if (token != null && !StringUtils.isEmpty(token)) {
                AuthUserEntity jwtUser = JwtUtils.getObjectFromToken(token, publicKey, AuthUserEntity.class);
                if (jwtUser != null && jwtUser.getUsername() != null) {
                    //查询userEntity
                    SimpleUserEntity simpleUserEntity= authRedisService.loadUserByUsername(jwtUser.getUsername(),token);
                    if(simpleUserEntity != null){
                        AuthUserEntity userEntity = new AuthUserEntity(simpleUserEntity);
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userEntity, null, userEntity.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
            }
            filterChain.doFilter(request, response);
        }catch(Exception e){
            log.error(e.getMessage(),e);
        }
    }
}