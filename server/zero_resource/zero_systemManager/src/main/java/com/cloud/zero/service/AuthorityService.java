package com.cloud.zero.service;

import com.cloud.zero.entities.auth.AuthorityEntity;
import com.cloud.zero.entities.auth.RoleEntity;
import com.cloud.zero.entities.common.PageEntity;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * AuthorityService
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-09-23
 */
public interface AuthorityService {

    /**
     * @Description 分页查询
     * @Param authorityEntity
     * @Param pageEntity
     * @Return com.github.pagehelper.PageInfo<com.cloud.zero.entities.auth.AuthorityEntity>
     */
    PageInfo<AuthorityEntity> queryPage(AuthorityEntity authorityEntity, PageEntity pageEntity);

    /**
     * @Description 查询列表
     * @Param authorityEntity
     * @Return java.util.List<com.cloud.zero.entities.auth.AuthorityEntity>
     */
    List<AuthorityEntity> queryList(AuthorityEntity authorityEntity);

    /**
     * @Description 查询可以作为父权限的权限列表
     * @Return java.util.List<com.cloud.zero.entities.auth.AuthorityEntity>
     */
    List<AuthorityEntity> queryListPAuth();

    /**
     * @Description 新增权限
     * @Param authorityEntity
     * @Param request
     * @Return java.lang.Integer
     */
    Integer insertAuth(AuthorityEntity authorityEntity, HttpServletRequest request) throws UnsupportedEncodingException;

    /**
     * @Description 修改权限
     * @Param authorityEntity
     * @Param request
     * @Return java.lang.Integer
     */
    Integer updateAuth(AuthorityEntity authorityEntity, HttpServletRequest request) throws UnsupportedEncodingException;

    /**
     * @Description 查询角色权限
     * @Param roleEntity
     * @Return java.util.List<com.cloud.zero.entities.auth.AuthorityEntity>
     */
    List<AuthorityEntity> queryRoleAuth(RoleEntity roleEntity);
}