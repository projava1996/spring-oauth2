//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.authorizationserver.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(
        name = "cmsrole_permission"
)
public class RolePermission implements Serializable {
    private static final long serialVersionUID = -1294565291522127473L;
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "role_id"
    )
    private Role role;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "permission_id"
    )
    private Permission permission;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Permission getPermission() {
        return this.permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
