package com.cloud.zero.service;

import com.cloud.zero.entities.auth.RoleEntity;
import com.cloud.zero.entities.auth.SimpleUserEntity;
import com.cloud.zero.entities.common.PageEntity;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * UserService
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-10-11
 */
public interface UserService {

    PageInfo<SimpleUserEntity> queryPage(SimpleUserEntity userEntity, PageEntity pageEntity);

    Integer insertUser(SimpleUserEntity userEntity, HttpServletRequest request) throws UnsupportedEncodingException;

    Integer updateUser(SimpleUserEntity userEntity, HttpServletRequest request) throws UnsupportedEncodingException;

    Integer delUser(SimpleUserEntity userEntity, HttpServletRequest request) throws UnsupportedEncodingException;

    Integer insertUserRole(SimpleUserEntity userEntity, HttpServletRequest request) throws UnsupportedEncodingException;
}