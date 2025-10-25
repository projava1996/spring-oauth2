//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.authorizationserver.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(
        name = "adm_role_menu"
)
public class RoleMenu implements Serializable {
    private static final long serialVersionUID = -7696026104717761993L;
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @ManyToOne
    @JoinColumn(
            name = "role_id"
    )
    private Role role;
    @ManyToOne
    @JoinColumn(
            name = "menu_id"
    )
    private Menu menu;

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

    public Menu getMenu() {
        return this.menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
