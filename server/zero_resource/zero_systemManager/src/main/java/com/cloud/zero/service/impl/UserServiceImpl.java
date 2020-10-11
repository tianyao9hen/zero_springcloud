package com.cloud.zero.service.impl;

import cn.hutool.core.util.IdUtil;
import com.cloud.zero.entities.auth.RoleEntity;
import com.cloud.zero.entities.auth.SimpleUserEntity;
import com.cloud.zero.entities.auth.UserRoleEntity;
import com.cloud.zero.entities.common.PageEntity;
import com.cloud.zero.mapper.UserMapper;
import com.cloud.zero.mapper.UserRoleMapper;
import com.cloud.zero.service.UserService;
import com.cloud.zero.utils.UserUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * UserServiceImpl
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-10-11
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public PageInfo<SimpleUserEntity> queryPage(SimpleUserEntity userEntity, PageEntity pageEntity) {
        Map<String,Object> map = new HashMap<>();
        map.put("o",userEntity);
        if(StringUtils.isNotBlank(pageEntity.getOrderBy())){
            PageHelper.startPage(pageEntity.getPageNum(),pageEntity.getPageSize(),pageEntity.getOrderBy());
        }else{
            PageHelper.startPage(pageEntity.getPageNum(),pageEntity.getPageSize());
        }
        List<SimpleUserEntity> roleList = userMapper.selectList(map);
        PageInfo<SimpleUserEntity> pageInfo = new PageInfo<>(roleList);
        return pageInfo;
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public Integer insertUser(SimpleUserEntity userEntity, HttpServletRequest request) throws UnsupportedEncodingException {
        if(StringUtils.isBlank(userEntity.getUsername())){
            return -1;
        }
        if(StringUtils.isBlank(userEntity.getName())){
            return -2;
        }
        List<SimpleUserEntity> list = userMapper.selectUserByUsername(userEntity.getUsername());
        if(list!=null && list.size() > 0){
            return -3;
        }
        userEntity.setId(IdUtil.simpleUUID());
        userEntity.setPassword("$2a$10$UhXp3CmMaIoUJIqE/hhf8OqDpS/HhCa88BeiC1xZmO0Eb9gxGKcX2");
        userEntity.setCreateBy(UserUtils.getNowUser(request).getId());
        userEntity.setCreateDate(new Date());
        return userMapper.insertUser(userEntity);
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public Integer insertUserRole(SimpleUserEntity userEntity, HttpServletRequest request) throws UnsupportedEncodingException {
        if(StringUtils.isBlank(userEntity.getId())){
            return -4;
        }
        String userId = UserUtils.getNowUser(request).getId();
        Date now = new Date();
        //删除
        userEntity.setUpdateBy(userId);
        userEntity.setUpdateDate(now);
        Integer delResult = userRoleMapper.delUserRole(userEntity);
        //新增
        List<String> roleIdList = userEntity.getRoleIdList();
        if(roleIdList == null || roleIdList.size() <= 0) return 1;
        List<UserRoleEntity> userRoleList = new ArrayList<>();
        for (String roleId : roleIdList) {
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setId(IdUtil.simpleUUID());
            userRoleEntity.setUserId(userEntity.getId());
            userRoleEntity.setRoleId(roleId);
            userRoleEntity.setCreateBy(userId);
            userRoleEntity.setCreateDate(now);
            userRoleList.add(userRoleEntity);
        }
        return userRoleMapper.insertUserRole(userRoleList);
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public Integer updateUser(SimpleUserEntity userEntity, HttpServletRequest request) throws UnsupportedEncodingException {
        if(StringUtils.isBlank(userEntity.getUsername())){
            return -1;
        }
        if(StringUtils.isBlank(userEntity.getName())){
            return -2;
        }
        if(StringUtils.isBlank(userEntity.getId())){
            return -4;
        }
        List<SimpleUserEntity> list = userMapper.selectUserByUsername(userEntity.getUsername());
        if(list != null){
            if(list.size() > 1){
                return -3;
            }else if(list.size() == 1 && !list.get(0).getId().equals(userEntity.getId())){
                return -3;
            }
        }
        userEntity.setUpdateBy(UserUtils.getNowUser(request).getId());
        userEntity.setUpdateDate(new Date());
        return userMapper.updateUser(userEntity);
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public Integer delUser(SimpleUserEntity userEntity, HttpServletRequest request) throws UnsupportedEncodingException {
        if(StringUtils.isBlank(userEntity.getId())){
            return -4;
        }
        userEntity.setUpdateBy(UserUtils.getNowUser(request).getId());
        userEntity.setUpdateDate(new Date());
        return userMapper.delUser(userEntity);
    }

}