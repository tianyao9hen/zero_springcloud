package com.cloud.zero.exception;

import com.cloud.zero.enumType.FwWebError;

/**
 * ServiceReturnException
 * 通用的异常实体类
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-08-25
 */
public class ServiceReturnException extends RuntimeException{

    private static final long serialVersionUID = 1972683609985154428L;

    private String code;
    private Object msg;

    public String getCode(){
        return code;
    }

    public Object getMsg(){
        return msg;
    }

    public ServiceReturnException(FwWebError fwWebError){
        super(fwWebError.msg.toString());
        this.msg = fwWebError.msg;
        this.code = fwWebError.code;
    }
}