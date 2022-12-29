package id.co.mandiri.parkinglot.service.wrapper;


import java.util.ArrayList;
import java.util.List;

public class ApplicationUserWrapper {

    private Long id;
    private String username;
    private String password;
    private String email;
    private Boolean enabled;
    private List<String> roleList;

    public ApplicationUserWrapper() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<String> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }

    /*TODO
    * fungsi dari addrole ini apa?*/

    public void addRole(String role){
        if(roleList == null){
            roleList = new ArrayList<>();
        }
        roleList.add(role);
    }


    @Override
    public String toString() {
        return "ApplicationUserWrapper{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", roleList=" + roleList +
                '}';
    }
}
