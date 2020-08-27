package com.cloud.zero.service.impl;

import com.cloud.zero.entities.AuthUserEntity;
import com.cloud.zero.service.RBACService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

/**
 * RBACServiceImpl
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-08-26
 */
@Service("rbacService")
public class RBACServiceImpl implements RBACService {

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        String token = request.getParameter("token");
        String path = request.getParameter("checkUrl");
        Object principal = authentication.getPrincipal();
        if(principal instanceof UserDetails){
            AuthUserEntity userDetails = ((AuthUserEntity)principal);
            List<GrantedAuthority> authorityList =
                    AuthorityUtils.commaSeparatedStringToAuthorityList(path);
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            //鉴权
            for (GrantedAuthority grantedAuthority : userDetails.getAuthorities()) {
                if(grantedAuthority.getAuthority().equals(authorityList.get(0).getAuthority())) {
                    request.setAttribute("nowUser",userDetails);
                    return true;
                }
            }
        }
        //System.out.println("---------------------这里是鉴权的地方---- "+token+" --" +path+ "---------------------");
        return false;
    }
}