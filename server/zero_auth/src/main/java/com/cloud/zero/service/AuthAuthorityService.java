package com.cloud.zero.service;

import com.cloud.zero.entities.AuthAuthorityEntity;

import java.util.List;

/**
 * AuthAuthorityService
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-09-21
 */
public interface AuthAuthorityService {

    List<AuthAuthorityEntity> queryAuthorityByUrl(String url);
}