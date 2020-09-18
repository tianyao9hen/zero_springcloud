package com.cloud.zero.service;

import com.cloud.zero.entities.DictEntity;
import com.cloud.zero.entities.common.OptionEntity;
import com.cloud.zero.entities.common.PageEntity;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * DictService
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-09-07
 */
public interface DictService {

    List<DictEntity> selectDict(DictEntity dictEntity);

    PageInfo<DictEntity> queryPage(DictEntity dictEntity, PageEntity pageEntity);

    Integer insertDict(DictEntity dictEntity, HttpServletRequest request) throws UnsupportedEncodingException;

    Integer updateDict(DictEntity dictEntity, HttpServletRequest request) throws UnsupportedEncodingException;

    List<DictEntity> selectGroup();

    List<OptionEntity> selectOptions(DictEntity dictEntity);
}