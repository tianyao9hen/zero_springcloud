package com.cloud.zero.entities.auth;

import com.cloud.zero.entities.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * RoleEntity
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-09-21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity extends CommonEntity {

    private static final long serialVersionUID = -6248840735563386141L;

    private String code;
    private String name;
    private String status;
    private String statusStr;
    private List<String> authIdList;
}