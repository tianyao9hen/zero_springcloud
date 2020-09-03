package com.cloud.zero.service;

import com.cloud.zero.entities.AuthMenu;

import java.util.List;

/**
 * AuthMenuService
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-09-03
 */
public interface AuthMenuService {

    List<AuthMenu> queryMenuByUrl(String url);
}