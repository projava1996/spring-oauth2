package com.authorizationserver.domain;


import javax.persistence.*;

@Entity(name = "CmsRole")
@Table(name = "cmsrole")
public class Role {
    private Long id;
    private String name;
    private String description;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "roleId")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}