package com.cloud.zero.service.impl;

import com.cloud.zero.entities.AuthMenu;
import com.cloud.zero.mapper.AuthMenuMapper;
import com.cloud.zero.service.AuthMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * AuthMenuServiceImpl
 * 权限菜单Service
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-09-03
 */
@Service
public class AuthMenuServiceImpl implements AuthMenuService {

    @Autowired
    private AuthMenuMapper authMenuMapper;

    @Override
    public List<AuthMenu> queryMenuByUrl(String url) {
        List<AuthMenu> authMenus = authMenuMapper.selectMenuByUrl(url);
        if(authMenus == null || authMenus.size() <= 0) return null;
        return authMenus;
    }
}