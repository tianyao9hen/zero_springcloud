package com.cloud.zero.service.impl;

import com.cloud.zero.entities.AuthMenu;
import com.cloud.zero.entities.AuthUserEntity;
import com.cloud.zero.mapper.AuthUserEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserDetailsServiceImpl
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-08-26
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AuthUserEntityMapper authUserEntityMapper;

    @Override
    public AuthUserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        //获得用户信息
        AuthUserEntity userDetails = authUserEntityMapper.findByUserName(username);

        if(userDetails == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        //获得用户角色列表
        List<String> roleCodes = authUserEntityMapper.findRoleByUserName(username);

        //通过角色列表获取权限列表
        List<AuthMenu> authorities = authUserEntityMapper.findAuthorityByRoleCodes(roleCodes);

        //转成用逗号分隔的字符串，为用户设置权限标识
        userDetails.setAuthorities(authorities);
        return userDetails;
    }
}