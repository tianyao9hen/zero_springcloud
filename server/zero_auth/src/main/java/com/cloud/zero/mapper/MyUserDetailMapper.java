package com.cloud.zero.mapper;

import com.cloud.zero.entities.AuthMenu;
import com.cloud.zero.entities.AuthUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * MyUserDetailMapper
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-08-18
 */
@Mapper
public interface MyUserDetailMapper {

    //根据username查询用户信息
    @Select("SELECT id,username,password,enabled FROM sys_user u WHERE u.username = #{username}")
    AuthUserEntity findByUserName(@Param("username") String username);

    //根据username查询用户角色
    @Select("SELECT role_code FROM sys_role r LEFT JOIN sys_user_role ur ON r.id = ur.role_id LEFT JOIN sys_user u ON u.id = ur.user_id WHERE u.username = #{username}")
    List<String> findRoleByUserName(@Param("username") String username);

    //根据用户角色查询用户权限
    @Select({
            "<script>",
            "SELECT m.id id,menu_pid,menu_name,url,icon,icon_color,m.sort sort,level,m.status status" ,
            "FROM sys_menu m " ,
            "LEFT JOIN sys_role_menu rm ON m.id = rm.menu_id " ,
            "LEFT JOIN sys_role r ON r.id = rm.role_id ",
            "WHERE r.role_code IN ",
            "<foreach collection='roleCodes' item='roleCode' open='(' separator=',' close=')'>",
            "#{roleCode}",
            "</foreach>",
            "</script>"
    })
    List<AuthMenu> findAuthorityByRoleCodes(@Param("roleCodes") List<String> roleCodes);
}