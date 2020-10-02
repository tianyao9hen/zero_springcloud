package com.cloud.zero.enumType;

/**
 * FwWebError
 * 通用的异常枚举类
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-07-10
 */
public enum FwWebError {

    //base
    UNKNOWN_ERROR("E_01","未知异常！"),

    //auth
    NO_LOGIN("AUTH_E_01","用户未登陆！"),
    EMPTY_LOGINNAME_OR_LOGINPASSWORD("AUTH_E_02","用户名和密码不能为空！"),
    WRONG_LOGNNAME_OR_LOGINPASSWORD("AUTH_E_03","用户名或密码输入有误！"),
    NO_PERMISSION("AUTH_E_04","用户权限不足！"),
    ENCRYPTION_ERRORS("AUTH_E_05","加密错误，请重新登陆！"),
    REFRESH_LOGIN("AUTH_E_06","请重新登陆！"),

    //redis
    REDIS_WRONG("REDIS_E_01","redis保存错误！"),

    //dict
    ALREADY_EXISTED("DICT_E_01","该字典项已存在！"),
    DICT_EDIT_ERROR("DICT_E_00","编辑失败！"),

    //authorty
    AUTH_UNKOWN_EROR("AUTHORITY_E_00","权限管理未知错误"),
    AUTH_REPEATED("AUTHORITY_E_O1","url重复"),
    AUTH_UPDATE_NO_ID("AUTHORITY_E_02","修改权限未提供ID");


    public final String code;
    public final Object msg;

    FwWebError(String code, Object msg){
        this.code = code;
        this.msg = msg;
    }
}