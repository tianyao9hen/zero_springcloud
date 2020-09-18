package com.cloud.zero.entities.common;

import lombok.Data;

import java.io.Serializable;

/**
 * PageEntity
 * 分页参数接收
 *
 * @author pxf
 * @version v1.0
 * @Date 2020-09-10
 */
@Data
public class PageEntity implements Serializable{

    private static final long serialVersionUID = 7255780703397689578L;

    private int pageNum;

    private int pageSize;

    private String orderBy;
}