package com.authorizationserver.dto.menu;

import lombok.Data;

import java.io.Serializable;

@Data
public class MenuDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String app;
    private String name;
    private String description;
    private String url;
    private String icon;
    private Integer orderNo;
    private Long parentId;
    private String parentName;
}