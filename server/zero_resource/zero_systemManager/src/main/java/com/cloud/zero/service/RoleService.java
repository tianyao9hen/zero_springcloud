package com.cloud.zero.service;

import com.cloud.zero.entities.auth.RoleEntity;
import com.cloud.zero.entities.common.PageEntity;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * RuleService
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-10-09
 */
public interface RoleService {

    PageInfo<RoleEntity> queryPage(RoleEntity roleEntity, PageEntity pageEntity);

    Integer insertRole(RoleEntity roleEntity, HttpServletRequest request) throws UnsupportedEncodingException;

    Integer updateRole(RoleEntity roleEntity,HttpServletRequest request) throws UnsupportedEncodingException;

    Integer delRole(RoleEntity roleEntity,HttpServletRequest request) throws UnsupportedEncodingException;

    Integer insertRoleAuth(RoleEntity roleEntity, HttpServletRequest request) throws UnsupportedEncodingException;
}