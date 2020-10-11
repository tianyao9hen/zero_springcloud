package com.cloud.zero.controller;

import com.cloud.zero.entities.auth.RoleEntity;
import com.cloud.zero.entities.common.PageEntity;
import com.cloud.zero.entities.common.ResultContent;
import com.cloud.zero.enumType.FwWebError;
import com.cloud.zero.service.RoleService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import javax.servlet.http.HttpServletRequest;

/**
 * RuleController
 * 角色管理
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-10-09
 */
@RestController
@Slf4j
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * @Description 分页查询
     * @Param roleEntity
     * @Return com.cloud.zero.entities.common.ResultContent<com.github.pagehelper.PageInfo<com.cloud.zero.entities.auth.RoleEntity>>
     */
    @GetMapping("/queryPage")
    public ResultContent<PageInfo<RoleEntity>> queryPage(RoleEntity roleEntity){
        log.info("role-queryPage");
        ResultContent<PageInfo<RoleEntity>> resultContent = new ResultContent<>();
        try{
            PageEntity pageEntity = new PageEntity();
            pageEntity.setPageNum(roleEntity.getPageNum());
            pageEntity.setPageSize(roleEntity.getPageSize());
            pageEntity.setOrderBy(roleEntity.getOrderBy());
            PageInfo<RoleEntity> pageInfo = roleService.queryPage(roleEntity,pageEntity);
            resultContent.setResult(pageInfo);
        }catch(Exception e){
            log.error(e.getMessage(),e);
            resultContent.setError(e);
        }
        return resultContent;
    }

    /**
     * @Description 新增角色
     * @Param roleEntity
     * @Return com.cloud.zero.entities.common.ResultContent<java.lang.String>
     */
    @PostMapping("/insertRole")
    public ResultContent<String> insertRole(@RequestBody RoleEntity roleEntity, HttpServletRequest request){
        log.info("role-insertRole");
        ResultContent<String> resultContent = new ResultContent<>();
        try{
            Integer result = roleService.insertRole(roleEntity,request);
            if(result > 0){
                resultContent.setSuccess(true);
            }else if(result == -1){
                resultContent.setError(FwWebError.ROLE_NO_CODE);
            }else if(result == -2){
                resultContent.setError(FwWebError.ROLE_NO_NAME);
            }else if(result == -3){
                resultContent.setError(FwWebError.ROLE_REPEATED_CODE);
            }else{
                resultContent.setError(FwWebError.ROLE_UNKOWN_EROR);
            }
        }catch(Exception e){
            log.error(e.getMessage(),e);
            resultContent.setError(e);
        }
        return resultContent;
    }

    /**
     * @Description 新增角色权限关联关系
     * @Param roleEntity
     * @Param request
     * @Return com.cloud.zero.entities.common.ResultContent<java.lang.String>
     */
    @PostMapping("/insertRoleAuth")
    public ResultContent<String> insertRoleAuth(@RequestBody RoleEntity roleEntity, HttpServletRequest request){
        log.info("role-insertRoleAuth");
        ResultContent<String> resultContent = new ResultContent<>();
        try{
            Integer result = roleService.insertRoleAuth(roleEntity,request);
            if(result > 0){
                resultContent.setSuccess(true);
            }else if(result == -4){
                resultContent.setError(FwWebError.ROLE_NO_ID);
            }else{
                resultContent.setError(FwWebError.ROLE_UNKOWN_EROR);
            }
        }catch(Exception e){
            log.error(e.getMessage(),e);
            resultContent.setError(e);
        }
        return resultContent;
    }

    /**
     * @Description 修改角色
     * @Param roleEntity
     * @Return com.cloud.zero.entities.common.ResultContent<java.lang.String>
     */
    @PutMapping("/updateRole")
    public ResultContent<String> updateRole(@RequestBody RoleEntity roleEntity,HttpServletRequest request){
        log.info("role-updateRole");
        ResultContent<String> resultContent = new ResultContent<>();
        try{
            Integer result = roleService.updateRole(roleEntity,request);
            if(result > 0){
                resultContent.setSuccess(true);
            }else if(result == -1){
                resultContent.setError(FwWebError.ROLE_NO_CODE);
            }else if(result == -2){
                resultContent.setError(FwWebError.ROLE_NO_NAME);
            }else if(result == -3){
                resultContent.setError(FwWebError.ROLE_REPEATED_CODE);
            }else if(result == -4){
                resultContent.setError(FwWebError.ROLE_NO_ID);
            }else{
                resultContent.setError(FwWebError.ROLE_UNKOWN_EROR);
            }
        }catch(Exception e){
            log.error(e.getMessage(),e);
            resultContent.setError(e);
        }
        return resultContent;
    }

    /**
     * @Description 删除角色
     * @Param roleEntity
     * @Return com.cloud.zero.entities.common.ResultContent<java.lang.String>
     */
    @DeleteMapping("/delRole")
    public ResultContent<String> delRole(@RequestBody RoleEntity roleEntity,HttpServletRequest request){
        log.info("role-delRole");
        ResultContent<String> resultContent = new ResultContent<>();
        try{
            Integer result = roleService.delRole(roleEntity,request);
            if(result > 0){
                resultContent.setSuccess(true);
            }else if(result == -4){
                resultContent.setError(FwWebError.ROLE_NO_ID);
            }else{
                resultContent.setError(FwWebError.ROLE_UNKOWN_EROR);
            }
        }catch(Exception e){
            log.error(e.getMessage(),e);
            resultContent.setError(e);
        }
        return resultContent;
    }
}