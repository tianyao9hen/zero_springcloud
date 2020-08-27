package com.cloud.zero.filter;

import com.cloud.zero.constant.BaseConstant;
import com.cloud.zero.entities.AuthUserEntity;
import com.cloud.zero.service.impl.UserDetailsServiceImpl;
import com.cloud.zero.utils.JwtUtils;
import com.cloud.zero.utils.RsaUtils;
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
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter{

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try{
            String token = request.getParameter("token");
            PublicKey publicKey = RsaUtils.getPublicKey(BaseConstant.PUB_KEY_PATH);
            if(token != null && !StringUtils.isEmpty(token)){
                AuthUserEntity jwtUser = JwtUtils.getObjectFromToken(token, publicKey, AuthUserEntity.class);
                AuthUserEntity userEntity = null;
                if(jwtUser.getUsername()!= null){
                    //查询userEntity
                    userEntity = userDetailsService.loadUserByUsername(jwtUser.getUsername());
                }
                if(userEntity!=null){
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userEntity,null,userEntity.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
            filterChain.doFilter(request,response);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}