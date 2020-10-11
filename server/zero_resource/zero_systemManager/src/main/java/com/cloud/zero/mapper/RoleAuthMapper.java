package com.cloud.zero.mapper;

import com.cloud.zero.entities.auth.RoleAuthEntity;
import com.cloud.zero.entities.auth.RoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * RoleAuthMapper
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-10-10
 */
@Mapper
public interface RoleAuthMapper {

    Integer delRoleAuth(RoleEntity roleEntity);

    Integer insertRoleAuth(@Param("list") List<RoleAuthEntity> roleAuthList);
}