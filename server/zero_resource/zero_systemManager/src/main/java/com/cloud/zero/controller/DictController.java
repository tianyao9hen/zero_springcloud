package com.cloud.zero.controller;

import com.cloud.zero.entities.system.DictEntity;
import com.cloud.zero.entities.common.OptionEntity;
import com.cloud.zero.entities.common.PageEntity;
import com.cloud.zero.entities.common.ResultContent;
import com.cloud.zero.enumType.FwWebError;
import com.cloud.zero.service.DictService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * DictController
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-09-07
 */
@RestController
@Slf4j
@RequestMapping("/dict")
public class DictController {

    @Autowired
    private DictService dictService;

    /**
     * @Description 查询字典项
     * @Param dictEntity
     * @Return com.cloud.zero.entities.common.ResultContent<java.util.List<com.cloud.zero.entities.system.DictEntity>>
     */
    @GetMapping("/selectDict")
    public ResultContent<List<DictEntity>> selectDict(DictEntity dictEntity){
        log.info("dict-selectDict");
        ResultContent<List<DictEntity>> result = new ResultContent<>();
        try{
            List<DictEntity> list = dictService.selectDict(dictEntity);
            result.setResult(list);
        }catch(Exception e){
            log.error(e.getMessage(),e);
            result.setError(e);
        }
        return result;
    }

    /**
     * @Description 从数据字典中查询选项
     * @Param dictEntity
     * @Return com.cloud.zero.entities.common.ResultContent<java.util.List<com.cloud.zero.entities.common.OptionEntity>>
     */
    @GetMapping("/getOptions")
    public ResultContent<List<OptionEntity>> selectOptions(DictEntity dictEntity){
        log.info("dict-getOptions");
        ResultContent<List<OptionEntity>> resultContent = new ResultContent<>();
        try{
            List<OptionEntity> list = dictService.selectOptions(dictEntity);
            resultContent.setResult(list);
        }catch(Exception e){
            log.error(e.getMessage(),e);
            resultContent.setError(e);
        }
        return resultContent;
    }

    /**
     * @Description 查询所有的字典组信息
     * @Return com.cloud.zero.entities.common.ResultContent<java.util.List<com.cloud.zero.entities.system.DictEntity>>
     */
    @GetMapping("/selectGroup")
    public ResultContent<List<DictEntity>> selectGroup(){
        log.info("dict-selectGroup");
        ResultContent<List<DictEntity>> resultContent = new ResultContent<>();
        try{
            List<DictEntity> list = dictService.selectGroup();
            resultContent.setResult(list);
        }catch(Exception e){
            log.error(e.getMessage(),e);
            resultContent.setError(e);
        }
        return resultContent;
    }

    /**
     * @Description 分页查询字典项
     * @Param dictEntity
     * @Return com.cloud.zero.entities.common.ResultContent<com.github.pagehelper.PageInfo<com.cloud.zero.entities.system.DictEntity>>
     */
    @GetMapping("/queryPage")
    public ResultContent<PageInfo<DictEntity>> queryPage(DictEntity dictEntity){
        log.info("dict-queryPage");
        ResultContent<PageInfo<DictEntity>> resultContent = new ResultContent<>();
        try{
            PageEntity pageEntity = new PageEntity();
            pageEntity.setPageNum(dictEntity.getPageNum());
            pageEntity.setPageSize(dictEntity.getPageSize());
            pageEntity.setOrderBy(dictEntity.getOrderBy());
            PageInfo<DictEntity> pageInfo = dictService.queryPage(dictEntity,pageEntity);
            resultContent.setResult(pageInfo);
        }catch(Exception e){
            log.error(e.getMessage(),e);
            resultContent.setError(e);
        }
        return resultContent;
    }

    /**
     * @Description 新增字典项
     * @Param dictEntity
     * @Param request
     * @Return com.cloud.zero.entities.common.ResultContent<java.lang.String>
     */
    @PostMapping("/insertDict")
    public ResultContent<String> insertDict(@RequestBody DictEntity dictEntity, HttpServletRequest request){
        log.info("dict-insertDict");
        ResultContent<String> resultContent = new ResultContent<>();
        try{
            Integer result = dictService.insertDict(dictEntity,request);
            if(result > 0){
                resultContent.setSuccess(true);
            }else if(result == -1){
                resultContent.setError(FwWebError.ALREADY_EXISTED);
            }else{
                resultContent.setSuccess(false);
            }
        }catch(Exception e){
            log.error(e.getMessage(),e);
            resultContent.setError(e);
        }
        return resultContent;
    }

    /**
     * @Description 修改字典项
     * @Param dictEntity
     * @Param request
     * @Return com.cloud.zero.entities.common.ResultContent<java.lang.String>
     */
    @PutMapping("/updateDict")
    public ResultContent<String> updateDict(@RequestBody DictEntity dictEntity,HttpServletRequest request){
        log.info("dict-updateDict");
        ResultContent<String> resultContent = new ResultContent<>();
        try{
            String id = dictEntity.getId();
            if(StringUtils.isBlank(id)){
                resultContent.setError(FwWebError.DICT_EDIT_ERROR);
                return resultContent;
            }
            Integer result = dictService.updateDict(dictEntity,request);
            if(result > 0){
                resultContent.setSuccess(true);
            }else if(result == -1){
                resultContent.setError(FwWebError.ALREADY_EXISTED);
            }else{
                resultContent.setSuccess(false);
            }
        }catch(Exception e){
            log.error(e.getMessage(),e);
            resultContent.setError(e);
        }
        return resultContent;
    }

    /**
     * @Description 删除字典项
     * @Param dictEntity
     * @Param request
     * @Return com.cloud.zero.entities.common.ResultContent<java.lang.String>
     */
    @DeleteMapping("/delDict")
    public ResultContent<String> delDict(@RequestBody DictEntity dictEntity,HttpServletRequest request){
        log.info("dict-delDict");
        ResultContent<String> resultContent = new ResultContent<>();
        try{
            String id = dictEntity.getId();
            if(StringUtils.isBlank(id)){
                resultContent.setError(FwWebError.DICT_EDIT_ERROR);
                return resultContent;
            }
            DictEntity con = new DictEntity();
            con.setId(dictEntity.getId());
            con.setActiveFlag("0");
            Integer result = dictService.updateDict(con,request);
            if(result > 0){
                resultContent.setSuccess(true);
            }else if(result == -1){
                resultContent.setError(FwWebError.ALREADY_EXISTED);
            }else{
                resultContent.setSuccess(false);
            }
        }catch(Exception e){
            log.error(e.getMessage(),e);
            resultContent.setError(e);
        }
        return resultContent;
    }

}