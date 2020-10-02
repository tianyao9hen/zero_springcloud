package com.cloud.zero.service.impl;

import com.cloud.zero.entities.AuthAuthorityEntity;
import com.cloud.zero.entities.AuthUserEntity;
import com.cloud.zero.entities.auth.RoleEntity;
import com.cloud.zero.mapper.AuthAuthorityMapper;
import com.cloud.zero.mapper.AuthRoleMapper;
import com.cloud.zero.mapper.AuthUserMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * UserDetailsServiceImpl
 * security提供的Service接口实现类
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-08-26
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AuthUserMapper authUserMapper;

    @Autowired
    private AuthAuthorityMapper authAuthorityMapper;

    @Autowired
    private AuthRoleMapper authRoleMapper;

    @Override
    public AuthUserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        //获得用户信息
        //AuthUserEntity userDetail = authUserEntityMapper.findByUserName(username);
        AuthUserEntity userDetail = authUserMapper.selectUserByUserName(username);

        if(userDetail == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        //获得用户角色列表
        //List<String> roleCodes = authUserEntityMapper.findRoleByUserName(username);
        List<RoleEntity> roleList = authRoleMapper.selectRoleByUserId(userDetail.getId());

        //通过角色列表获取权限列表
        //List<AuthMenu> authorities = authUserEntityMapper.findAuthorityByRoleCodes(roleCodes);
        List<AuthAuthorityEntity> authList =  authAuthorityMapper.selectAuthorityByRoleId(roleList);
        List<AuthAuthorityEntity> authTree = new ArrayList<>();

        //递归得到权限树
        for (AuthAuthorityEntity authEntity : authList) {
            if(StringUtils.equals(authEntity.getPid(),"0")){ //pid为0的一定是根目录
                AuthAuthorityEntity authEntityTree = getAuthChilds(authEntity,authList);
                authTree.add(authEntityTree);
            }
        }

        //转成用逗号分隔的字符串，为用户设置权限标识
        userDetail.setAuthorities(authTree);
        return userDetail;
    }

    private AuthAuthorityEntity getAuthChilds(AuthAuthorityEntity parentAuth, List<AuthAuthorityEntity> authList) {
        List<AuthAuthorityEntity> childs = new ArrayList<>();
        for (AuthAuthorityEntity authEntity : authList) {
            if(StringUtils.equals(authEntity.getPid(),parentAuth.getId())){
                if(StringUtils.equals(authEntity.getType(),"2")){
                    childs.add(authEntity);
                }else{
                    AuthAuthorityEntity anthEntityTree = getAuthChilds(authEntity, authList);
                    childs.add(anthEntityTree);
                }
            }
        }
        parentAuth.setChilds(childs);
        return parentAuth;
    }
}