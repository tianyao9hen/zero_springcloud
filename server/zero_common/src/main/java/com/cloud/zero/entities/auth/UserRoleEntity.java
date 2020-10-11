package com.cloud.zero.entities.auth;

import com.cloud.zero.entities.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserRoleEntity
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-10-11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleEntity extends CommonEntity {

    private static final long serialVersionUID = -9124586723305405931L;

    private String userId;

    private String roleId;
}