package com.cloud.zero.entities.common;

import com.cloud.zero.enumType.FwWebError;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ResultContant
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-08-25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultConstant implements Serializable{

    private static final long serialVersionUID = -8871294006366435253L;

    private Boolean success;

    private Object result;

    private Object error;

    private String errorCode;

    public void setError(FwWebError fwWebError){
        setError(fwWebError.code,fwWebError.msg);
    }

    private void setError(String errorCode,Object error){
        success = false;
        this.error = error;
        this.errorCode = errorCode;
    }

    public void setResult(Object result){
        success = true;
        this.result = result;
    }

    public Boolean isSuccess(){
        return success;
    }


}