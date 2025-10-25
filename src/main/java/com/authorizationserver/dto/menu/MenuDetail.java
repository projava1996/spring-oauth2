package com.authorizationserver.dto.menu;

import lombok.Data;

@Data
public class MenuDetail {
    private Long id;
    private String icon;
    private String name;
    private String url;
    private String parentId;
    private String orderNo;
    private String app;
    private String description;
    private Boolean anonymous;
    private Boolean disable;
}
