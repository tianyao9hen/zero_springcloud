package com.cloud.zero.entities.auth;

import com.cloud.zero.entities.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RoleAuthEntity
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-10-10
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleAuthEntity extends CommonEntity {

    private static final long serialVersionUID = -4452352242205474125L;

    private String roleId;
    private String authorityId;
}