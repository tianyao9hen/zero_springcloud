package com.cloud.zero.entities.auth;


import com.cloud.zero.entities.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * MyUserDetails
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-08-25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserEntity extends CommonEntity{

    private static final long serialVersionUID = 3023106876004019126L;

    private String username;
    private String password;
    private Date createTime;
    private Long orgId;
    private Boolean enabled;
    private String phone;
    private String email;

}