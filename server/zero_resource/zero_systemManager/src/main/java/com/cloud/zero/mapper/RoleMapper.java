package com.cloud.zero.mapper;

import com.cloud.zero.entities.auth.RoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * RuleMapper
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-10-09
 */
@Mapper
public interface RoleMapper {

    /**
     * @Description 查询
     * @Param map
     * @Return java.util.List<com.cloud.zero.entities.auth.RoleEntity>
     */
    List<RoleEntity> selectList(Map<String, Object> map);

    /**
     * @Description 新增角色
     * @Param roleEntity
     * @Return java.lang.Integer
     */
    Integer insertRole(RoleEntity roleEntity);

    /**
     * @Description 根据code查询角色
     * @Param con
     * @Return java.util.List<com.cloud.zero.entities.auth.RoleEntity>
     */
    List<RoleEntity> selectRoleByCode(RoleEntity con);

    /**
     * @Description 修改角色
     * @Param roleEntity
     * @Return java.lang.Integer
     */
    Integer updateRole(RoleEntity roleEntity);

    /**
     * @Description 假删除角色
     * @Param roleEntity
     * @Return java.lang.Integer
     */
    Integer delRole(RoleEntity roleEntity);
}