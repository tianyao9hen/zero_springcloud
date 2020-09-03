package com.cloud.zero.mapper;

import com.cloud.zero.entities.AuthMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * AuthMenuMapper
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-09-03
 */
@Mapper
public interface AuthMenuMapper {

    @Select("SELECT * FROM sys_menu where url = #{url}")
    List<AuthMenu> selectMenuByUrl(@Param("url") String url);
}