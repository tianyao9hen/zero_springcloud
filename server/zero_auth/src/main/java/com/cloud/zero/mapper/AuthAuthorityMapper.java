package com.cloud.zero.mapper;

import com.cloud.zero.entities.AuthAuthorityEntity;
import com.cloud.zero.entities.auth.RoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * AuthAuthorityMapper
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-09-21
 */
@Mapper
public interface AuthAuthorityMapper {

    /**
     * @Description 通过url查询权限
     * @Param url
     * @Return java.util.List<com.cloud.zero.entities.AuthAuthorityEntity>
     */
    List<AuthAuthorityEntity> selectAuthorityByUrl(@Param("url") String url);

    /**
     * @Description 通过角色列表获取权限列表
     * @Param list 角色列表
     * @Return java.util.List<com.cloud.zero.entities.AuthAuthorityEntity>
     */
    List<AuthAuthorityEntity> selectAuthorityByRoleId(@Param("list") List<RoleEntity> list);
}
