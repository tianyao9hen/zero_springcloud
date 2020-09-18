package com.cloud.zero.mapper;

import com.cloud.zero.entities.DictEntity;
import com.cloud.zero.entities.common.OptionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Map;

/**
 * DictMapper
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-09-07
 */
@Mapper
public interface DictMapper {

    List<DictEntity> selectDict(Map<String,Object> map);

    int insertDict(DictEntity dictEntity);

    int updateDict(DictEntity dictEntity);

    List<DictEntity> selectGroup();

    List<OptionEntity> selectOptions(@Param("groupCode") String groupCode);
}