package com.cloud.zero.mapper;

import com.cloud.zero.entities.auth.RoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
 * AuthRoleMapper
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-09-21
 */
@Mapper
public interface AuthRoleMapper {

    /**
     * @Description  获得用户角色列表
     * @Param id 用户id
     * @Return java.util.List<com.cloud.zero.entities.auth.RoleEntity>
     */
    List<RoleEntity> selectRoleByUserId(@Param("userId") String id);
}