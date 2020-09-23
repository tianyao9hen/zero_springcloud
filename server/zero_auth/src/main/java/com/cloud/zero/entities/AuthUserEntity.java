package com.cloud.zero.entities;


import com.cloud.zero.entities.auth.AuthorityEntity;
import com.cloud.zero.entities.auth.SimpleUserEntity;
import com.cloud.zero.entities.common.CommonEntity;
import lombok.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * AuthUserEntity
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-08-25
 */
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserEntity extends CommonEntity implements UserDetails{

    private static final long serialVersionUID = 3023106876004019126L;

    @Setter
    private String username;
    @Setter
    private String password;

    @Setter
    @Getter
    private String orgId;

    @Setter
    @Getter
    private String status;

    @Setter
    @Getter
    private String phone;

    @Setter
    @Getter
    private String email;

    @Setter
    @Getter
    private String token;

    @Setter
    private List<AuthAuthorityEntity> authorities;

    @Override
    public List<AuthAuthorityEntity> getAuthorities(){
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if(StringUtils.isNotBlank(status) && "1".equals(status)){
            return true;
        }
        return false;
    }

    /**
    * @Description 构造方法，通过simpleUserEntity创建AuthUserEntity
    * @Param userEntity
    * @Return
    */
    public AuthUserEntity(SimpleUserEntity userEntity){
        setId(userEntity.getId());
        setUsername(userEntity.getUsername());
        setPassword(userEntity.getPassword());
        setPhone(userEntity.getPhone());
        setEmail(userEntity.getEmail());
        setOrgId(userEntity.getOrgId());
        setCreateBy(userEntity.getCreateBy());
        setCreateDate(userEntity.getCreateDate());
        setUpdateBy(userEntity.getUpdateBy());
        setUpdateDate(userEntity.getUpdateDate());
        setStatus(userEntity.getStatus());
        setRemarks(userEntity.getRemarks());
        setActiveFlag(userEntity.getActiveFlag());
        setToken(userEntity.getToken());

        //权限树
        List<AuthAuthorityEntity> authTree = new ArrayList<>();
        List<AuthorityEntity> authorityTree = userEntity.getAuthorities();
        if(authorityTree != null && authorityTree.size() > 0){
            for (AuthorityEntity authorityEntity : authorityTree) {
                AuthAuthorityEntity authEntity = new AuthAuthorityEntity(authorityEntity);
                authTree.add(authEntity);
            }
        }
        setAuthorities(authTree);
    }

    /**
     * @Description 得到封装好的SimpleUserEntity对象
     * @Return com.cloud.zero.entities.auth.SimpleUserEntity
     */
    public SimpleUserEntity packageSimpleUser(){
        SimpleUserEntity userEntity = new SimpleUserEntity();
        userEntity.setId(getId());
        userEntity.setUsername(getUsername());
        userEntity.setPassword(getPassword());
        userEntity.setPhone(getPhone());
        userEntity.setEmail(getEmail());
        userEntity.setOrgId(getOrgId());
        userEntity.setCreateBy(getCreateBy());
        userEntity.setCreateDate(getCreateDate());
        userEntity.setUpdateBy(getUpdateBy());
        userEntity.setUpdateDate(getUpdateDate());
        userEntity.setStatus(getStatus());
        userEntity.setRemarks(getRemarks());
        userEntity.setActiveFlag(getActiveFlag());
        userEntity.setToken(getToken());
        userEntity.setAuthorities(getSimpleAuthorityList());
        return userEntity;
    }

    /**
     * @Description 得到该用户的所有权限对象
     * @Return java.util.List<com.cloud.zero.entities.auth.AuthorityEntity>
     */
    public List<AuthorityEntity> getSimpleAuthorityList(){
        List<AuthorityEntity> authorityTree = new ArrayList<>();
        if(authorities == null) return authorityTree;
        for (AuthAuthorityEntity authority : authorities) {
            List<AuthorityEntity> simpleChilds = authority.getSimpleChilds();
            AuthorityEntity simpleAuthority = authority.getSimpleAuthority();
            simpleAuthority.setChilds(simpleChilds);
            authorityTree.add(simpleAuthority);
        }
        return authorityTree;
    }
}