package com.authorizationserver.domain;

import javax.persistence.*;

@Entity
@Table(name = "cmsroleuser", uniqueConstraints = @UniqueConstraint(columnNames = {"userId", "roleId"}))
public class UserRole {

    private Long id;
    private Role role;
    private Users user;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "roleId")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @ManyToOne
    @JoinColumn(name = "userId")
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}