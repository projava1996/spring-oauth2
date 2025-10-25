package com.authorizationserver.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "adm_menu")
public class Menu implements Serializable {
    private static final long serialVersionUID = 3751698453542475456L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String url;
    private String icon;

    @Column(name = "order_no")
    private Integer orderNo;

    @Enumerated(EnumType.STRING)
    private App app;

    private Boolean disable;
    private Boolean anonymous;

    public enum App {
        FCMS, ADMIN
    }

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Menu parent;

    @OneToMany(mappedBy = "parent", orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Menu> children;

    @OneToMany(mappedBy = "menu")
    private Set<RoleMenu> roleMenus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Set<Menu> getChildren() {
        return children;
    }

    public void setChildren(Set<Menu> children) {
        this.children = children;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    public Boolean getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Boolean anonymous) {
        this.anonymous = anonymous;
    }

    public Set<RoleMenu> getRoleMenus() {
        return roleMenus;
    }

    public void setRoleMenus(Set<RoleMenu> roleMenus) {
        this.roleMenus = roleMenus;
    }
}