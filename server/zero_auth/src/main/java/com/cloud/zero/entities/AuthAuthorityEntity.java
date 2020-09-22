package com.cloud.zero.entities;

import com.cloud.zero.entities.auth.AuthorityEntity;
import com.cloud.zero.entities.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 * 鉴权中心 权限entity
 * AuthAuthorityEntity
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-09-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthAuthorityEntity extends CommonEntity implements GrantedAuthority {

    private static final long serialVersionUID = -5617315627380731690L;

    private String pid;     //父id
    private String name;    //权限名称
    private String url;     //权限url或按钮、请求标识，要求唯一
    private String icon;    //图标
    private String sort;    //排序
    private String type;    //权限类型 0：父节点 1页面节点 2请求按钮节点
    private String status;  //状态 0：停用 1：启用

    @Override
    public String getAuthority() {
        return url;
    }

    public AuthAuthorityEntity(AuthorityEntity entity){
        setId(entity.getId());
        setPid(entity.getPid());
        setName(entity.getName());
        setUrl(entity.getUrl());
        setIcon(entity.getIcon());
        setSort(entity.getSort());
        setType(entity.getType());
        setStatus(entity.getStatus());
        setCreateBy(entity.getCreateBy());
        setCreateDate(entity.getCreateDate());
        setUpdateBy(entity.getUpdateBy());
        setUpdateDate(entity.getUpdateDate());
        setActiveFlag(entity.getActiveFlag());
        setRemarks(entity.getRemarks());
    }

    public AuthorityEntity getSimpleAuthority(){
        AuthorityEntity entity = new AuthorityEntity();
        entity.setId(getId());
        entity.setPid(getPid());
        entity.setName(getName());
        entity.setUrl(getUrl());
        entity.setIcon(getIcon());
        entity.setSort(getSort());
        entity.setType(getType());
        entity.setStatus(getStatus());
        entity.setCreateBy(getCreateBy());
        entity.setCreateDate(getCreateDate());
        entity.setUpdateBy(getUpdateBy());
        entity.setUpdateDate(getUpdateDate());
        entity.setActiveFlag(getActiveFlag());
        entity.setRemarks(getRemarks());
        return entity;
    }

}