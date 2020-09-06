package com.cloud.zero.service.impl;

import com.cloud.zero.constant.BaseConstant;
import com.cloud.zero.entities.AuthMenu;
import com.cloud.zero.entities.AuthUserEntity;
import com.cloud.zero.service.AuthMenuService;
import com.cloud.zero.service.AuthService;
import com.cloud.zero.service.RBACService;
import com.cloud.zero.service.UserEntityService;
import com.cloud.zero.utils.JwtUtils;
import com.cloud.zero.utils.RsaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.PublicKey;
import java.util.Collection;
import java.util.List;

/**
 * RBACServiceImpl
 * 自定义的鉴权逻辑
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-08-26
 */
@Service("rbacService")
public class RBACServiceImpl implements RBACService {

    @Autowired
    private AuthService authRedisService;

    @Autowired
    private UserEntityService userEntityService;

    @Autowired
    private AuthMenuService authMenuService;

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        try {
            String token = request.getParameter("token");
            String path = request.getParameter("checkUrl");
            Object principal = authentication.getPrincipal();
            if(principal instanceof UserDetails){
                AuthUserEntity userDetails = ((AuthUserEntity)principal);

                //得到path和用户权限
                List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(path);

                //查询所有的权限，如果根本权限表中没有这个权限，则认为不设限制
                List<AuthMenu> authMenus = authMenuService.queryMenuByUrl(authorityList.get(0).getAuthority());
                if(authMenus == null) {
                    request.setAttribute("nowUser",userDetails);
                    return true;
                }
                Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

                //鉴权
                for (GrantedAuthority grantedAuthority : authorities) {
                    if(grantedAuthority.getAuthority().equals(authorityList.get(0).getAuthority())) {
                        //鉴权通过
                        //判断token有效期，决定是否刷新token
                        String newToken = userEntityService.refreshTokenByDuration(token);
                        if(StringUtils.isEmpty(newToken)){
                            return false;
                        }
                        userDetails.setToken(newToken);
                        request.setAttribute("nowUser",userDetails);
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}