package gt.umg.viajes.entities;

import java.util.Date;

/**
 * Created by wilver on 28/05/17.
 */

public class GenericEntity {
    private Integer id;
    private boolean active;
    private Date createdAt;

    public GenericEntity() {
    }

    public GenericEntity(Integer id, boolean active) {
        this.id = id;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
