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
     *@Param 公钥所在地址
     *@Return
     */
    public static final String PUB_KEY_PATH = "static\\ras.pub";

    /**
     *@Description
     *@Param 私钥所在地址
     *@Return
     */
    public static final String PRI_KEY_PATH = "static\\ras.pri";

    /**
     *@Description
     *@Param 保存在redis中的用户信息头
     *@Return
     */
    public static final String LOGIN_REDIS_PRE = "JWT_USER_TOKEN:";

    /**
     * @Description 
     * @Param jwt超时时间
     * @Return
     */
    public static final Long LOGIN_JWT_TIMEOUT = 7200L;

    /**
     *@Description
     *@Param 保存在redis中的用户信息有效时长，单位秒
     *@Return
     */
    public static final Long LOGIN_REDIS_TIMEOUT = LOGIN_JWT_TIMEOUT;

    /**
     *@Description
     *@Param 保存在redis中的用户信息有效时长，单位分
     *@Return
     */
    public static final Long LOGIN_JWT_TIMEOUT_MINUTE = LOGIN_JWT_TIMEOUT / 60;//120L;

    public static final String JWT_HEADER_NAME = "jwt_header_name";
}