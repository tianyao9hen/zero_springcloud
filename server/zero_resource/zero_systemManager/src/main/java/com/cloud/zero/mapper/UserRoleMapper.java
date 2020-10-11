package com.cloud.zero.mapper;

import com.cloud.zero.entities.auth.RoleEntity;
import com.cloud.zero.entities.auth.SimpleUserEntity;
import com.cloud.zero.entities.auth.UserRoleEntity;
import com.sun.tracing.dtrace.ModuleAttributes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * UserRoleMapper
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-10-11
 */
@Mapper
public interface UserRoleMapper {

    /**
     * @Description 删除用户角色
     * @Param userEntity
     * @Return java.lang.Integer
     */
    Integer delUserRole(SimpleUserEntity userEntity);

    /**
     * @Description 新增用户角色
     * @Param userRoleList
     * @Return java.lang.Integer
     */
    Integer insertUserRole(@Param("list") List<UserRoleEntity> userRoleList);
}