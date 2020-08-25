package com.cloud.zero.constant;

/**
 * BaseConstant
 * 通用公共字段
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-08-25
 */
public class BaseConstant {

    /**
     *@Description
     *@Param 保存在redis中的用户信息头
     *@Return
     */
    public static final String LOGIN_REDIS_PRE = "JWT_USER_TOKEN:";

    /**
     *@Description
     *@Param 保存在redis中的用户信息有效时长，单位秒
     *@Return
     */
    public static final Long LOGIN_REDIS_TIMEOUT = 7200L;

    /**
     *@Description
     *@Param 保存在redis中的用户信息有效时长，单位分
     *@Return
     */
    public static final Long LOGIN_REDIS_TIMEOUT_MINUTE = LOGIN_REDIS_TIMEOUT / 60;//120L;
}