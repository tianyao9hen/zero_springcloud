package com.cloud.zero.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * RBACService
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-08-26
 */
public interface RBACService {

    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}