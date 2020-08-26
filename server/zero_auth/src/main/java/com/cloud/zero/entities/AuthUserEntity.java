package com.cloud.zero.entities;


import com.cloud.zero.entities.auth.SimpleMenu;
import com.cloud.zero.entities.auth.SimpleUserEntity;
import com.cloud.zero.entities.common.CommonEntity;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.util.ArrayList;
import java.util.Date;
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
    private Date createTime;

    @Setter
    @Getter
    private Long orgId;

    @Setter
    private Boolean enabled;

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
    private List<AuthMenu> authorities;

    @Override
    public List<AuthMenu> getAuthorities(){
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
        if(enabled == null) return false;
        return enabled;
    }

    public SimpleUserEntity packageSimpleUser(){
        SimpleUserEntity userEntity = new SimpleUserEntity();
        userEntity.setId(getId());
        userEntity.setUsername(getUsername());
        userEntity.setPassword(getPassword());
        userEntity.setPhone(getPhone());
        userEntity.setEmail(getEmail());
        userEntity.setOrgId(getOrgId());
        userEntity.setCreateTime(getCreateTime());
        userEntity.setEnabled(enabled);
        userEntity.setToken(getToken());
        userEntity.setAuthorities(packageSimpleMenu());
        return userEntity;
    }

    public List<SimpleMenu> packageSimpleMenu(){
        List<SimpleMenu> menuList = new ArrayList<>();
        for (AuthMenu authority : authorities) {
            menuList.add(authority.getSimpleMenu());
        }
        return menuList;
    }
}