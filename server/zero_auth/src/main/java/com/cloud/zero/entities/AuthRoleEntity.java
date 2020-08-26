package com.cloud.zero.entities;

import com.cloud.zero.entities.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * AuthRoleEntity
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-08-25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRoleEntity extends CommonEntity {

    private String roleName;

    private String roleDesc;

    private String roleCode;

    private String sort;

    private String status;

    private Date createTime;
}