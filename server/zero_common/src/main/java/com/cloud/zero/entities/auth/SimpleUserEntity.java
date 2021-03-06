package com.cloud.zero.entities.auth;

import com.cloud.zero.entities.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


/**
 * SimpleUserEntity
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-08-26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleUserEntity extends CommonEntity {

    private static final long serialVersionUID = -5501130885388084225L;

    private String username;

    private String password;

    private String name;

    private String orgId;

    private String phone;

    private String email;

    private String status;

    private String statusStr;

    private String token;

    private List<AuthorityEntity> authorities;

    private List<String> roleIdList;
}