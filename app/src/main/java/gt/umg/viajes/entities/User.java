package gt.umg.viajes.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wilver on 13/04/17.
 */

public class User {

    private static final long serialVersionUID = 935523708898636964L;

    private Integer id;

    private String name;

    private String lastname;

    private String email;

    private String password;

    private Date createdAt;

    private boolean active;

    private List<UserRole> roles = new ArrayList();

    public User() {
    }

    public User(Integer id, String name, String lastname, String email, String password, Date createdAt, boolean active) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

}
