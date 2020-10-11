package com.cloud.zero.controller;

import com.cloud.zero.entities.auth.AuthorityEntity;
import com.cloud.zero.entities.auth.RoleEntity;
import com.cloud.zero.entities.common.PageEntity;
import com.cloud.zero.entities.common.ResultContent;
import com.cloud.zero.entities.system.DictEntity;
import com.cloud.zero.enumType.FwWebError;
import com.cloud.zero.service.AuthorityService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 权限管理controller
 * AuthorityController
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-09-23
 */
@RestController
@Slf4j
@RequestMapping("/authority")
public class   AuthorityController {

    @Autowired
    private AuthorityService authorityService;

    /**
     * @Description 分页查询（并不使用）
     * @Param authorityEntity
     * @Return com.cloud.zero.entities.common.ResultContent<com.github.pagehelper.PageInfo<com.cloud.zero.entities.auth.AuthorityEntity>>
     */
    @GetMapping("/queryPage")
    public ResultContent<PageInfo<AuthorityEntity>> queryPage(AuthorityEntity authorityEntity){
        log.info("authority-queryPage");
        ResultContent<PageInfo<AuthorityEntity>> resultContent = new ResultContent<>();
        try{
            PageEntity pageEntity = new PageEntity();
            pageEntity.setPageNum(authorityEntity.getPageNum());
            pageEntity.setPageSize(authorityEntity.getPageSize());
            pageEntity.setOrderBy(authorityEntity.getOrderBy());
            PageInfo<AuthorityEntity> pageInfo =  authorityService.queryPage(authorityEntity,pageEntity);
            resultContent.setResult(pageInfo);
        }catch(Exception e){
            log.error(e.getMessage(),e);
            resultContent.setError(e);
        }
        return resultContent;
    }

    /**
     * @Description 根据角色查询权限列表
     * @Param roleEntity
     * @Return com.cloud.zero.entities.common.ResultContent<java.util.List<com.cloud.zero.entities.auth.AuthorityEntity>>
     */
    @GetMapping("/queryRoleAuth")
    public ResultContent<List<AuthorityEntity>> queryRoleAuth(RoleEntity roleEntity){
        log.info("authority-queryRoleAuth");
        ResultContent<List<AuthorityEntity>> resultContent = new ResultContent<>();
        try{
            List<AuthorityEntity> list = authorityService.queryRoleAuth(roleEntity);
            resultContent.setResult(list);
        }catch(Exception e){
            log.error(e.getMessage(),e);
            resultContent.setError(e);
        }
        return resultContent;
    }

    /**
     * @Description 查询列表
     * @Param authorityEntity
     * @Return com.cloud.zero.entities.common.ResultContent<java.util.List<com.cloud.zero.entities.auth.AuthorityEntity>>
     */
    @GetMapping("/queryList")
    public ResultContent<List<AuthorityEntity>> queryList(AuthorityEntity authorityEntity){
        log.info("authority-queryList");
        ResultContent<List<AuthorityEntity>> resultContent = new ResultContent<>();
        try{
            List<AuthorityEntity> list = authorityService.queryList(authorityEntity);
            resultContent.setResult(list);
        }catch(Exception e){
            log.error(e.getMessage(),e);
            resultContent.setError(e);
        }
        return resultContent;
    }

    /**
     * @Description 查询可以作为父权限的权限列表
     * @Return com.cloud.zero.entities.common.ResultContent<java.util.List<com.cloud.zero.entities.auth.AuthorityEntity>>
     */
    @GetMapping("/queryListPAuth")
    public ResultContent<List<AuthorityEntity>> queryListPAuth(){
        log.info("authority-queryListPAuth");
        ResultContent<List<AuthorityEntity>> resultContent = new ResultContent<>();
        try{
            List<AuthorityEntity> list = authorityService.queryListPAuth();
            resultContent.setResult(list);
        }catch(Exception e){
            log.error(e.getMessage(),e);
            resultContent.setError(e);
        }
        return resultContent;
    }

    /**
     * @Description 新增权限
     * @Param authorityEntity
     * @Param request
     * @Return com.cloud.zero.entities.common.ResultContent<java.lang.String>
     */
    @PostMapping("/insertAuth")
    public ResultContent<String> insertAuth(@RequestBody AuthorityEntity authorityEntity, HttpServletRequest request){
        log.info("authority-insertAuth");
        ResultContent<String> resultContent = new ResultContent<>();
        try{
            Integer result =  authorityService.insertAuth(authorityEntity,request);
            if(result > 0){
                resultContent.setSuccess(true);
            }else if(result == -1){
                resultContent.setError(FwWebError.AUTH_REPEATED);
            }else{
                resultContent.setError(FwWebError.AUTH_UNKOWN_EROR);
            }
        }catch(Exception e){
            log.error(e.getMessage(),e);
            resultContent.setError(e);
        }
        return resultContent;
    }

    /**
     * @Description 修改权限
     * @Param authorityEntity
     * @Param request
     * @Return com.cloud.zero.entities.common.ResultContent<java.lang.String>
     */
    @PutMapping("/updateAuth")
    public ResultContent<String> updateAuth(@RequestBody AuthorityEntity authorityEntity,HttpServletRequest request){
        log.info("authority-updateAuth");
        ResultContent<String> resultContent = new ResultContent<>();
        try{
            Integer result =authorityService.updateAuth(authorityEntity,request);
            if(result > 0){
                resultContent.setSuccess(true);
            }else if(result == -1){
                resultContent.setError(FwWebError.AUTH_REPEATED);
            }else if(result == -2){
                resultContent.setError(FwWebError.AUTH_UPDATE_NO_ID);
            }else{
                resultContent.setError(FwWebError.AUTH_UNKOWN_EROR);
            }
        }catch(Exception e){
            log.error(e.getMessage(),e);
            resultContent.setError(e);
        }
        return resultContent;
    }

    /**
     * @Description 删除权限
     * @Param dictEntity
     * @Param request
     * @Return com.cloud.zero.entities.common.ResultContent<java.lang.String>
     */
    @DeleteMapping("/delAuth")
    public ResultContent<String> delDict(@RequestBody AuthorityEntity authorityEntity, HttpServletRequest request){
        log.info("authority-delAuth");
        ResultContent<String> resultContent = new ResultContent<>();
        try{
            AuthorityEntity con = new AuthorityEntity();
            con.setId(authorityEntity.getId());
            con.setActiveFlag("0");
            Integer result = authorityService.updateAuth(con,request);
            if(result > 0){
                resultContent.setSuccess(true);
            }else if(result == -1){
                resultContent.setError(FwWebError.AUTH_REPEATED);
            }else if(result == -2){
                resultContent.setError(FwWebError.AUTH_UPDATE_NO_ID);
            }else{
                resultContent.setError(FwWebError.AUTH_UNKOWN_EROR);
            }
        }catch(Exception e){
            log.error(e.getMessage(),e);
            resultContent.setError(e);
        }
        return resultContent;
    }
}