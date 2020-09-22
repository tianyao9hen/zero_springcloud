package com.cloud.zero.mapper;

import com.cloud.zero.entities.AuthUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * AuthUserMapper
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-09-21
 */
@Mapper
public interface AuthUserMapper {

    /**
     * @Description 根据username查询用户信息
     * @Param username
     * @Return com.cloud.zero.entities.AuthUserEntity
     */
    AuthUserEntity selectUserByUserName(@Param("username") String username);
}