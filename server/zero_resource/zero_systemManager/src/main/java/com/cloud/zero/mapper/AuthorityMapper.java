package com.cloud.zero.mapper;

import com.cloud.zero.entities.auth.AuthorityEntity;
import com.cloud.zero.entities.auth.RoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * AuthorityMapper
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-09-23
 */
@Mapper
public interface AuthorityMapper {

    /**
     * @Description 查询权限列表
     * @Param map
     * @Return java.util.List<com.cloud.zero.entities.auth.AuthorityEntity>
     */
    List<AuthorityEntity> selectList(Map<String, Object> map);

    /**
     * @Description 查询可以作为父权限的权限列表
     * @Return java.util.List<com.cloud.zero.entities.auth.AuthorityEntity>
     */
    List<AuthorityEntity> selectListPAuth();

    /**
     * @Description 根据url查询Authority
     * @Param url
     * @Return java.util.List<com.cloud.zero.entities.auth.AuthorityEntity>
     */
    List<AuthorityEntity> selectAuthorityByUrl(@Param("url") String url);

    /**
     * @Description 新增权限
     * @Param authorityEntity
     * @Return java.lang.Integer
     */
    Integer insertAuth(AuthorityEntity authorityEntity);

    /**
     * @Description 修改权限
     * @Param authorityEntity
     * @Return java.lang.Integer
     */
    Integer updateAuth(AuthorityEntity authorityEntity);

    /**
     * @Description 查询角色权限
     * @Param roleEntity
     * @Return java.util.List<com.cloud.zero.entities.auth.AuthorityEntity>
     */
    List<AuthorityEntity> selectRoleAuth(RoleEntity roleEntity);
}