package com.cloud.zero.entities;

import com.cloud.zero.entities.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据字典实体类
 * SysDict
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-09-07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictEntity extends CommonEntity{

    private static final long serialVersionUID = -6649085314895939667L;

    private String groupCode;

    private String groupName;

    private String itemCode;

    private String itemName;

    private String status;

    private String statusStr;

    //创建查询条件
    public DictEntity(String groupCode, String itemCode) {
        setGroupCode(groupCode);
        setItemCode(itemCode);
    }
}