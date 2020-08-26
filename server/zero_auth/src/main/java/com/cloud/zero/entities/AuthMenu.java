package com.cloud.zero.entities;

import com.cloud.zero.entities.auth.SimpleMenu;
import com.cloud.zero.entities.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 * AuthMenu
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-08-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthMenu extends CommonEntity implements GrantedAuthority {

    private static final long serialVersionUID = -7348054588828405028L;

    private Long menuPid;
    private Boolean isLeaf;
    private String menuName;
    private String url;
    private String icon;
    private String iconColor;
    private Integer sort;
    private Integer level;
    private Boolean status;

    @Override
    public String getAuthority() {
        return url;
    }

    public SimpleMenu getSimpleMenu(){
        SimpleMenu menu = new SimpleMenu();
        menu.setId(getId());
        menu.setMenuPid(getMenuPid());
        menu.setIsLeaf(getIsLeaf());
        menu.setMenuName(getMenuName());
        menu.setUrl(getUrl());
        menu.setIcon(getIcon());
        menu.setIconColor(getIconColor());
        menu.setSort(getSort());
        menu.setLevel(getLevel());
        menu.setStatus(getStatus());
        return menu;
    }
}