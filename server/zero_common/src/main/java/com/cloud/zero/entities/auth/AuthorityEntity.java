package com.cloud.zero.entities.auth;

import com.cloud.zero.entities.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * AuthorityEntity
 * 权限entity
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-09-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityEntity extends CommonEntity {

    private static final long serialVersionUID = 363406491383198548L;

    private String pid;     //父id
    private String name;    //权限名称
    private String url;     //权限url或按钮、请求标识，要求唯一
    private String icon;    //图标
    private String sort;    //排序
    private String type;    //权限类型 0：父节点 1页面节点 2请求按钮节点
    private String typeStr;
    private String status;  //状态 0：停用 1：启用
    private String statusStr;

    private List<AuthorityEntity> childs;
    //private Boolean hasChild; //是否存在子函数，用于前端展示判断

    public AuthorityEntity checkChilds(List<AuthorityEntity> authorityList) {
        this.childs = new ArrayList<>();
        for (AuthorityEntity entity : authorityList) {
            if(entity.getPid().equals(this.getId())){
                if(!"2".equals(entity.getType())){
                    entity = entity.checkChilds(authorityList);
                }
                this.childs.add(entity);
            }
        }
        //if(this.childs.size() > 0) this.hasChild = true;
        return this;
    }
}