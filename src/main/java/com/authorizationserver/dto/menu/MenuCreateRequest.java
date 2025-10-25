package com.authorizationserver.dto.menu;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MenuCreateRequest {
    @NotBlank(message = "app is require field")
    private String app;
    @NotBlank(message = "name is require field")
    private String name;
    private String description;
    private String url;
    private String icon;
    private Long parent;
    private Integer orderNo;
    private Boolean disable;
    private Boolean anonymous;
}