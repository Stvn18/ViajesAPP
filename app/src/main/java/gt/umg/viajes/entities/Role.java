package gt.umg.viajes.entities;

/**
 * Created by wilver on 13/04/17.
 */

public class Role {

    private static final long serialVersionUID = -77075554466114071L;

    private Integer id;

    private String roleName;

    private boolean active;

    public Role() {
    }

    public Role(Integer id, String roleName, boolean active) {
        this.id = id;
        this.roleName = roleName;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
