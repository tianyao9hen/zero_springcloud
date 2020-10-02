package com.cloud.zero.service.impl;

import cn.hutool.core.util.IdUtil;
import com.cloud.zero.entities.system.DictEntity;
import com.cloud.zero.entities.auth.SimpleUserEntity;
import com.cloud.zero.entities.common.OptionEntity;
import com.cloud.zero.entities.common.PageEntity;
import com.cloud.zero.mapper.DictMapper;
import com.cloud.zero.service.DictService;
import com.cloud.zero.utils.UserUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * DictServiceImpl
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-09-07
 */
@Service
@Transactional(readOnly = true)
public class DictServiceImpl implements DictService {

    @Autowired
    private DictMapper dictMapper;

    /**
     * @Description 根据关键字查询全部字典项
     * @Param dictEntity
     * @Return java.util.List<com.cloud.zero.entities.system.DictEntity>
     */
    @Override
    public List<DictEntity> selectDict(DictEntity dictEntity) {
        dictEntity.setStatus("1");
        Map<String,Object> map = new HashMap<>();
        map.put("o",dictEntity);
        return dictMapper.selectDict(map);
    }

    /**
     * @Description 分页查询
     * @Param dictEntity
     * @Param pageEntity
     * @Return com.github.pagehelper.PageInfo<com.cloud.zero.entities.system.DictEntity>
     */
    @Override
    public PageInfo<DictEntity> queryPage(DictEntity dictEntity, PageEntity pageEntity) {
        Map<String,Object> map = new HashMap<>();
        map.put("o",dictEntity);
        if(StringUtils.isNotBlank(pageEntity.getOrderBy())){
            PageHelper.startPage(pageEntity.getPageNum(),pageEntity.getPageSize(),pageEntity.getOrderBy());
        }else{
            PageHelper.startPage(pageEntity.getPageNum(),pageEntity.getPageSize());
        }
        List<DictEntity> dictEntityList = dictMapper.selectDict(map);
        PageInfo<DictEntity> pageInfo = new PageInfo<>(dictEntityList);
        return pageInfo;
    }

    /**
     * @Description 新增字典项
     * @Param dictEntity
     * @Param request
     * @Return java.lang.Integer
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Integer insertDict(DictEntity dictEntity, HttpServletRequest request) throws UnsupportedEncodingException {
        //检查是否有重复
        DictEntity con = new DictEntity(dictEntity.getGroupCode(),dictEntity.getItemCode());
        Map<String,Object> map = new HashMap<>();
        map.put("o",con);
        List<DictEntity> oldDictList = dictMapper.selectDict(map);
        if(oldDictList != null && oldDictList.size() > 0){
            return -1;
        }
        //新增
        SimpleUserEntity nowUser = UserUtils.getNowUser(request);
        dictEntity.setId(IdUtil.simpleUUID());
        dictEntity.setCreateBy(nowUser.getId());
        dictEntity.setCreateDate(new Date());
        return dictMapper.insertDict(dictEntity);
    }

    /**
     * @Description 修改字典项
     * @Param dictEntity
     * @Param request
     * @Return java.lang.Integer
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Integer updateDict(DictEntity dictEntity, HttpServletRequest request) throws UnsupportedEncodingException {
        //检查是否有重复
        if(dictEntity.getActiveFlag().equals("1")){
            DictEntity con = new DictEntity(dictEntity.getGroupCode(),dictEntity.getItemCode());
            Map<String,Object> map = new HashMap<>();
            map.put("o",con);
            List<DictEntity> oldDictList = dictMapper.selectDict(map);
            if(oldDictList != null){
                if(oldDictList.size() > 1) return -1;
                if(oldDictList.size() == 1 && !oldDictList.get(0).getId().equals(dictEntity.getId())) return -1;
            }
        }

        //修改
        SimpleUserEntity nowUser = UserUtils.getNowUser(request);
        dictEntity.setUpdateBy(nowUser.getId());
        dictEntity.setUpdateDate(new Date());
        return dictMapper.updateDict(dictEntity);
    }

    /**
     * @Description 查询所有的字典组信息
     * @Return java.util.List<com.cloud.zero.entities.system.DictEntity>
     */
    @Override
    public List<DictEntity> selectGroup() {
        return dictMapper.selectGroup();
    }

    /**
     * @Description 从数据字典中查询选项
     * @Param dictEntity
     * @Return java.util.List<com.cloud.zero.entities.common.OptionEntity>
     */
    @Override
    public List<OptionEntity> selectOptions(DictEntity dictEntity) {
        return dictMapper.selectOptions(dictEntity.getGroupCode());
    }
}