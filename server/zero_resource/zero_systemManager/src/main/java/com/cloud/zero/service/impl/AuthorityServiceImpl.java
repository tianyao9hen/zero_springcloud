package com.cloud.zero.service.impl;

import cn.hutool.core.util.IdUtil;
import com.cloud.zero.entities.auth.AuthorityEntity;
import com.cloud.zero.entities.auth.RoleEntity;
import com.cloud.zero.entities.auth.SimpleUserEntity;
import com.cloud.zero.entities.common.PageEntity;
import com.cloud.zero.mapper.AuthorityMapper;
import com.cloud.zero.service.AuthorityService;
import com.cloud.zero.utils.UserUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * AuthorityServiceImpl
 * 权限管理
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-09-23
 */
@Service
@Transactional(readOnly = true)
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityMapper authorityMapper;

    /**
     * @Description 分页查询
     * @Param authorityEntity
     * @Param pageEntity
     * @Return com.github.pagehelper.PageInfo<com.cloud.zero.entities.auth.AuthorityEntity>
     */
    @Override
    public PageInfo<AuthorityEntity> queryPage(AuthorityEntity authorityEntity, PageEntity pageEntity) {
        Map<String,Object> map = new HashMap<>();
        map.put("o",authorityEntity);
        if(StringUtils.isNotBlank(pageEntity.getOrderBy())){
            PageHelper.startPage(pageEntity.getPageNum(),pageEntity.getPageSize(),pageEntity.getOrderBy());
        }else{
            PageHelper.startPage(pageEntity.getPageNum(),pageEntity.getPageSize());
        }
        List<AuthorityEntity> authorityList = authorityMapper.selectList(map);
        PageInfo<AuthorityEntity> pageInfo = new PageInfo<>(authorityList);
        return pageInfo;
    }

    /**
     * @Description 查询列表 
     * @Param authorityEntity
     * @Return java.util.List<com.cloud.zero.entities.auth.AuthorityEntity>
     */
    @Override
    public List<AuthorityEntity> queryList(AuthorityEntity authorityEntity) {
        Map<String,Object> map = new HashMap<>();
        map.put("o",authorityEntity);
        List<AuthorityEntity> authorityList = authorityMapper.selectList(map);
        List<AuthorityEntity> resultList = new ArrayList<>();
        for (AuthorityEntity entity : authorityList) {
            if(StringUtils.equals(entity.getPid(),"0")){
                AuthorityEntity auth = entity.checkChilds(authorityList);
                resultList.add(auth);
            }
        }
        return resultList;
    }

    /**
     * @Description 查询可以作为父权限的权限列表
     * @Return java.util.List<com.cloud.zero.entities.auth.AuthorityEntity>
     */
    @Override
    public List<AuthorityEntity> queryListPAuth() {
        List<AuthorityEntity> authorityList = authorityMapper.selectListPAuth();
        return authorityList;
    }

    /**
     * @Description 查询角色权限
     * @Param roleEntity
     * @Return java.util.List<com.cloud.zero.entities.auth.AuthorityEntity>
     */
    @Override
    public List<AuthorityEntity> queryRoleAuth(RoleEntity roleEntity) {
        List<AuthorityEntity> authorityList = authorityMapper.selectRoleAuth(roleEntity);
        return authorityList;
    }

    /**
     * @Description 新增权限
     * @Param authorityEntity
     * @Param request
     * @Return java.lang.Integer
     */
    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public Integer insertAuth(AuthorityEntity authorityEntity, HttpServletRequest request) throws UnsupportedEncodingException {
        if(StringUtils.equals(authorityEntity.getType(), "0")){
            //如果是父节点，检查url为空，且pid设置为0，
            authorityEntity.setPid("0");
        }else {
            //如果不是父节点，检查url不能重复
            if(StringUtils.isNotBlank(authorityEntity.getUrl())){
                List<AuthorityEntity> list = authorityMapper.selectAuthorityByUrl(authorityEntity.getUrl());
                if(list.size() > 0) {
                    //url重复
                    return -1;
                }
            }
        }
        SimpleUserEntity nowUser = UserUtils.getNowUser(request);
        authorityEntity.setId(IdUtil.simpleUUID());
        authorityEntity.setCreateBy(nowUser.getId());
        authorityEntity.setCreateDate(new Date());
        return authorityMapper.insertAuth(authorityEntity);
    }

    /**
     * @Description 修改权限
     * @Param authorityEntity
     * @Param request
     * @Return java.lang.Integer
     */
    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public Integer updateAuth(AuthorityEntity authorityEntity, HttpServletRequest request) throws UnsupportedEncodingException {
        if(StringUtils.isBlank(authorityEntity.getId())){
            return -2;
        }
        if(StringUtils.equals(authorityEntity.getType(), "0")){
            //如果是父节点，检查url为空，且pid设置为0，
            authorityEntity.setPid("0");
        }else{
            //如果不是父节点，检查url不能重复
            if(StringUtils.isNotBlank(authorityEntity.getUrl())){
                List<AuthorityEntity> list = authorityMapper.selectAuthorityByUrl(authorityEntity.getUrl());
                if(list.size() > 1 || (list.size() == 1 && !StringUtils.equals(list.get(0).getId(),authorityEntity.getId()))){
                    //url重复
                    return -1;
                }
            }
        }
        SimpleUserEntity nowUser = UserUtils.getNowUser(request);
        authorityEntity.setUpdateBy(nowUser.getId());
        authorityEntity.setUpdateDate(new Date());
        return authorityMapper.updateAuth(authorityEntity);
    }
}