package com.cloud.zero.controller;

import com.cloud.zero.entities.auth.RoleEntity;
import com.cloud.zero.entities.auth.SimpleUserEntity;
import com.cloud.zero.entities.common.PageEntity;
import com.cloud.zero.entities.common.ResultContent;
import com.cloud.zero.enumType.FwWebError;
import com.cloud.zero.service.UserService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * UserController
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-10-11
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @Description 分页查询
     * @Param userEntity
     * @Return com.cloud.zero.entities.common.ResultContent<com.github.pagehelper.PageInfo<com.cloud.zero.entities.auth.SimpleUserEntity>>
     */
    @GetMapping("/queryPage")
    public ResultContent<PageInfo<SimpleUserEntity>> queryPage(SimpleUserEntity userEntity){
        log.info("user-queryPage");
        ResultContent<PageInfo<SimpleUserEntity>> resultContent = new ResultContent<>();
        try{
            PageEntity pageEntity = new PageEntity();
            pageEntity.setPageNum(userEntity.getPageNum());
            pageEntity.setPageSize(userEntity.getPageSize());
            pageEntity.setOrderBy(userEntity.getOrderBy());
            PageInfo<SimpleUserEntity> pageInfo = userService.queryPage(userEntity,pageEntity);
            resultContent.setResult(pageInfo);
        }catch(Exception e){
            log.error(e.getMessage(),e);
            resultContent.setError(e);
        }
        return resultContent;
    }

    /**
     * @Description 新增用户
     * @Param userEntity
     * @Param request
     * @Return com.cloud.zero.entities.common.ResultContent<java.lang.String>
     */
    @PostMapping("/insertUser")
    public ResultContent<String> insertUser(@RequestBody SimpleUserEntity userEntity, HttpServletRequest request){
        log.info("user-insertUser");
        ResultContent<String> resultContent = new ResultContent<>();
        try{
            Integer result = userService.insertUser(userEntity,request);
            if(result > 0){
                resultContent.setSuccess(true);
            }else if(result == -1){
                resultContent.setError(FwWebError.USER_NO_USERNAME);
            }else if(result == -2){
                resultContent.setError(FwWebError.USER_NO_NAME);
            }else if(result == -3){
                resultContent.setError(FwWebError.USER_REPEATED_USERNAME);
            }else{
                resultContent.setError(FwWebError.USER_UNKOWN_EROR);
            }
        }catch(Exception e){
            log.error(e.getMessage(),e);
            resultContent.setError(e);
        }
        return resultContent;
    }

    /**
     * @Description 用户绑定角色
     * @Param userEntity
     * @Param request
     * @Return com.cloud.zero.entities.common.ResultContent<java.lang.String>
     */
    @PostMapping("/insertUserRole")
    public ResultContent<String> insertUserRole(@RequestBody SimpleUserEntity userEntity,HttpServletRequest request){
        log.info("user-insertUserRole");
        ResultContent<String> resultContent = new ResultContent<>();
        try{
            Integer result = userService.insertUserRole(userEntity,request);
            if(result > 0){
                resultContent.setSuccess(true);
            }else if(result == -4){
                resultContent.setError(FwWebError.USER_NO_ID);
            }else{
                resultContent.setError(FwWebError.USER_UNKOWN_EROR);
            }
        }catch(Exception e){
            log.error(e.getMessage(),e);
            resultContent.setError(e);
        }
        return resultContent;
    }

    /**
     * @Description 修改用户
     * @Param userEntity
     * @Param request
     * @Return com.cloud.zero.entities.common.ResultContent<java.lang.String>
     */
    @PutMapping("/updateUser")
    public ResultContent<String> updateUser(@RequestBody SimpleUserEntity userEntity,HttpServletRequest request){
        log.info("user-updateUser");
        ResultContent<String> resultContent = new ResultContent<>();
        try{
            Integer result = userService.updateUser(userEntity,request);
            if(result > 0){
                resultContent.setSuccess(true);
            }else if(result == -1){
                resultContent.setError(FwWebError.USER_NO_USERNAME);
            }else if(result == -2){
                resultContent.setError(FwWebError.USER_NO_NAME);
            }else if(result == -3){
                resultContent.setError(FwWebError.USER_REPEATED_USERNAME);
            }else if(result == -4){
                resultContent.setError(FwWebError.USER_NO_ID);
            }else{
                resultContent.setError(FwWebError.USER_UNKOWN_EROR);
            }
        }catch(Exception e){
            log.error(e.getMessage(),e);
            resultContent.setError(e);
        }
        return resultContent;
    }

    /**
     * @Description 删除用户
     * @Param userEntity
     * @Param request
     * @Return com.cloud.zero.entities.common.ResultContent<java.lang.String>
     */
    @DeleteMapping("/delUser")
    public ResultContent<String> delUser(@RequestBody SimpleUserEntity userEntity,HttpServletRequest request){
        log.info("user-delUser");
        ResultContent<String> resultContent = new ResultContent<>();
        try{
            Integer result = userService.delUser(userEntity,request);
            if(result > 0){
                resultContent.setSuccess(true);
            }else if(result == -4){
                resultContent.setError(FwWebError.USER_NO_ID);
            }else{
                resultContent.setError(FwWebError.USER_UNKOWN_EROR);
            }
        }catch(Exception e){
            log.error(e.getMessage(),e);
            resultContent.setError(e);
        }
        return resultContent;
    }
}