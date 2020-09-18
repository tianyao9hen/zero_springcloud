package com.cloud.zero.entities.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 下拉框选项实体类
 * SelectOption
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-09-12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionEntity extends CommonEntity {

    private static final long serialVersionUID = -5453046281035441532L;

    private String label;

    private String value;

}