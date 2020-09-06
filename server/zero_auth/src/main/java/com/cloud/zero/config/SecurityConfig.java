package com.cloud.zero.config;

import com.cloud.zero.filter.JwtAuthenticationTokenFilter;
import com.cloud.zero.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * SecurityConfig
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-08-26
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;


    @Bean(name = "org.springframework.security.authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
    /**
     * @Description  用户权限的控制
     * @param http
     * @Return void
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            // 允许跨域访问 SpringSecurity会自动寻找name=corsConfigurationSource的Bean
            .cors()
            .and()
                ////关闭跨域保护
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/login").permitAll() //不做拦截的请求
                .antMatchers("/auth/getUserEntityByToken","/auth/logout").permitAll()
                .antMatchers("/auth/checkUser").access("@rbacService.hasPermission(request,authentication)")
                .anyRequest().authenticated()
            .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
        ;

    }

    /**
     * @Description 加载用户信息和权限角色信息
     * @param auth
     * @Return void
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * @Description 对于静态资源的处理方法
     * @param web
     * @Return void
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        //将项目中的静态资源路径开发出来
        web.ignoring()
                .antMatchers("/css/**","/fonts/**","/img/**","/js/**");
    }

/*    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "DELETE", "PUT", "OPTIONS"));
        configuration.applyPermitDefaultValues();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("*//**", configuration);
        return source;
    }*/

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}