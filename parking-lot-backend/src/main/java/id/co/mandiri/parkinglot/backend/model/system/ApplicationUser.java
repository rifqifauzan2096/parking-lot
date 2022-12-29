package id.co.mandiri.parkinglot.backend.model.system;


import id.co.mandiri.parkinglot.common.EntityBase;

import javax.persistence.*;


@Entity
@Table(name = "app_user", uniqueConstraints = @UniqueConstraint(columnNames = {"username", "password"}))
public class ApplicationUser extends EntityBase {

    @Column(name = "username", length = 50, nullable = false, unique = true)
    private String username;

    @Column(name = "password" ,nullable = false)
    private String password;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "enabled")
    private Boolean enabled;

    public ApplicationUser() {
    }

    public ApplicationUser(String username, String password, String email, Boolean enabled) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
