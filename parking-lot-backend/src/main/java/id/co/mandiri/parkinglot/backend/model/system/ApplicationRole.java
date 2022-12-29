package id.co.mandiri.parkinglot.backend.model.system;


import id.co.mandiri.parkinglot.common.EntityBase;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
public class ApplicationRole extends EntityBase{


    @Column(name = "role_name", length = 20)
    private String role;

    @Column(name = "username")
    private String username;

    public ApplicationRole() {
    }

    public ApplicationRole(String role, String username) {
        this.role = role;
        this.username = username;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
