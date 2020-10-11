package com.cloud.zero.service.impl;

import cn.hutool.core.util.IdUtil;
import com.cloud.zero.entities.auth.RoleAuthEntity;
import com.cloud.zero.entities.auth.RoleEntity;
import com.cloud.zero.entities.auth.SimpleUserEntity;
import com.cloud.zero.entities.common.PageEntity;
import com.cloud.zero.mapper.RoleAuthMapper;
import com.cloud.zero.mapper.RoleMapper;
import com.cloud.zero.service.RoleService;
import com.cloud.zero.utils.UserUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * RuleServiceImpl
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-10-09
 */
@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleAuthMapper roleAuthMapper;

    /**
     * @Description 分页查询
     * @Param roleEntity
     * @Param pageEntity
     * @Return com.github.pagehelper.PageInfo<com.cloud.zero.entities.auth.RoleEntity>
     */
    @Override
    public PageInfo<RoleEntity> queryPage(RoleEntity roleEntity, PageEntity pageEntity) {
        Map<String,Object> map = new HashMap<>();
        map.put("o",roleEntity);
        if(StringUtils.isNotBlank(pageEntity.getOrderBy())){
            PageHelper.startPage(pageEntity.getPageNum(),pageEntity.getPageSize(),pageEntity.getOrderBy());
        }else{
            PageHelper.startPage(pageEntity.getPageNum(),pageEntity.getPageSize());
        }
        List<RoleEntity> roleList = roleMapper.selectList(map);
        PageInfo<RoleEntity> pageInfo = new PageInfo<>(roleList);
        return pageInfo;
    }

    /**
     * @Description 查询全部角色
     * @Param roleEntity
     * @Return java.util.List<com.cloud.zero.entities.auth.RoleEntity>
     */
    @Override
    public List<RoleEntity> queryList(RoleEntity roleEntity) {
        Map<String,Object> map = new HashMap<>();
        map.put("o",roleEntity);
        return roleMapper.selectList(map);
    }

    /**
     * @Description 查询指定用户的角色
     * @Param userEntity
     * @Return java.util.List<com.cloud.zero.entities.auth.RoleEntity>
     */
    @Override
    public List<RoleEntity> queryListUserRole(SimpleUserEntity userEntity) {
        Map<String,Object> map = new HashMap<>();
        map.put("o",userEntity);
        return roleMapper.selectRoleByUser(map);
    }

    /**
     * @Description 新增角色
     * @Param roleEntity
     * @Return java.lang.Integer
     */
    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public Integer insertRole(RoleEntity roleEntity, HttpServletRequest request) throws UnsupportedEncodingException {
        if(StringUtils.isBlank(roleEntity.getCode())){
            return -1;
        }
        if(StringUtils.isBlank(roleEntity.getName())){
            return -2;
        }
        if(StringUtils.isBlank(roleEntity.getStatus())){
            roleEntity.setStatus("1");
        }
        RoleEntity con = new RoleEntity();
        con.setCode(roleEntity.getCode());
        List<RoleEntity> roleList = roleMapper.selectRoleByCode(con);
        if(roleList != null && roleList.size() > 0){
            return -3;
        }
        roleEntity.setId(IdUtil.simpleUUID());
        roleEntity.setCreateBy(UserUtils.getNowUser(request).getId());
        roleEntity.setCreateDate(new Date());
        return roleMapper.insertRole(roleEntity);
    }

    /**
     * @Description 修改角色
     * @Param roleEntity
     * @Return java.lang.Integer
     */
    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public Integer updateRole(RoleEntity roleEntity,HttpServletRequest request) throws UnsupportedEncodingException {
        if(StringUtils.isBlank(roleEntity.getId())){
            return -4;
        }
        if(StringUtils.isBlank(roleEntity.getCode())){
            return -1;
        }
        if(StringUtils.isBlank(roleEntity.getName())){
            return -2;
        }
        if(StringUtils.isBlank(roleEntity.getStatus())){
            roleEntity.setStatus("1");
        }
        RoleEntity con = new RoleEntity();
        con.setCode(roleEntity.getCode());
        List<RoleEntity> roleList = roleMapper.selectRoleByCode(con);
        if(roleList != null){
            if(roleList.size() > 1){
                return -3;
            }else if(roleList.size() == 1 && !roleList.get(0).getId().equals(roleEntity.getId())){
                return -3;
            }
        }
        roleEntity.setUpdateBy(UserUtils.getNowUser(request).getId());
        roleEntity.setUpdateDate(new Date());
        return roleMapper.updateRole(roleEntity);
    }

    /**
     * @Description 删除角色
     * @Param roleEntity
     * @Return java.lang.Integer
     */
    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public Integer delRole(RoleEntity roleEntity,HttpServletRequest request) throws UnsupportedEncodingException {
        if(StringUtils.isBlank(roleEntity.getId())){
            return -4;
        }
        roleEntity.setUpdateBy(UserUtils.getNowUser(request).getId());
        roleEntity.setUpdateDate(new Date());
        return roleMapper.delRole(roleEntity);
    }

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public Integer insertRoleAuth(RoleEntity roleEntity, HttpServletRequest request) throws UnsupportedEncodingException {
        if(StringUtils.isBlank(roleEntity.getId())){
            return -4;
        }
        String userId = UserUtils.getNowUser(request).getId();
        Date now = new Date();
        //删除
        roleEntity.setUpdateBy(userId);
        roleEntity.setUpdateDate(now);
        Integer delResult = roleAuthMapper.delRoleAuth(roleEntity);
        //新增
        List<String> authIdList = roleEntity.getAuthIdList();
        if(authIdList == null || authIdList.size() <= 0) return 1;
        List<RoleAuthEntity> roleAuthList = new ArrayList<>();
        for (String authId : authIdList) {
            RoleAuthEntity roleAuth = new RoleAuthEntity();
            roleAuth.setId(IdUtil.simpleUUID());
            roleAuth.setRoleId(roleEntity.getId());
            roleAuth.setAuthorityId(authId);
            roleAuth.setCreateBy(userId);
            roleAuth.setCreateDate(now);
            roleAuthList.add(roleAuth);
        }
        Integer insertResult = roleAuthMapper.insertRoleAuth(roleAuthList);
        return insertResult;
    }

}