package com.cloud.zero.service.impl;

import com.cloud.zero.entities.AuthAuthorityEntity;
import com.cloud.zero.mapper.AuthAuthorityMapper;
import com.cloud.zero.service.AuthAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * AuthAuthorityServiceImpl
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-09-21
 */
@Service
@Transactional(readOnly = true)
public class AuthAuthorityServiceImpl implements AuthAuthorityService {

    @Autowired
    private AuthAuthorityMapper authAuthorityMapper;

    @Override
    public List<AuthAuthorityEntity> queryAuthorityByUrl(String url) {
        List<AuthAuthorityEntity> authList = authAuthorityMapper.selectAuthorityByUrl(url);
        if(authList == null || authList.size() <= 0) return null;
        return authList;
    }
}