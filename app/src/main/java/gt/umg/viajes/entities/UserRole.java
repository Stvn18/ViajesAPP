package gt.umg.viajes.entities;

/**
 * Created by wilver on 13/04/17.
 */

public class UserRole {

    private static final long serialVersionUID = -9217084727225541903L;

    private Integer id;

    private Role role;

    private boolean active;

    public UserRole() {
    }

    public UserRole(Integer id, Role role, boolean active) {
        this.id = id;
        this.role = role;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
