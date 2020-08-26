package com.cloud.zero.entities.auth;

import com.cloud.zero.entities.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SimpleMenu
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-08-26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleMenu extends CommonEntity {

    private static final long serialVersionUID = 4942425959555309853L;

    private Long menuPid;
    private Boolean isLeaf;
    private String menuName;
    private String url;
    private String icon;
    private String iconColor;
    private Integer sort;
    private Integer level;
    private Boolean status;
}