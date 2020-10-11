package com.cloud.zero.mapper;

import com.cloud.zero.entities.auth.SimpleUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * UserMapper
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-10-11
 */
@Mapper
public interface UserMapper {

    /**
     * @Description 查询
     * @Param map
     * @Return java.util.List<com.cloud.zero.entities.auth.SimpleUserEntity>
     */
    List<SimpleUserEntity> selectList(Map<String, Object> map);

    /**
     * @Description 根据用户账户查询
     * @Param username
     * @Return java.util.List<com.cloud.zero.entities.auth.SimpleUserEntity>
     */
    List<SimpleUserEntity> selectUserByUsername(@Param("username") String username);

    /**
     * @Description 新增用户
     * @Param userEntity
     * @Return java.lang.Integer
     */
    Integer insertUser(SimpleUserEntity userEntity);

    /**
     * @Description 修改用户
     * @Param userEntity
     * @Return java.lang.Integer
     */
    Integer updateUser(SimpleUserEntity userEntity);

    /**
     * @Description 删除用户
     * @Param userEntity
     * @Return java.lang.Integer
     */
    Integer delUser(SimpleUserEntity userEntity);
}