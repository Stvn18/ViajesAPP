package gt.umg.viajes.entities;

/**
 * Created by wilver on 17/05/17.
 */

public class Hotel {

    private Integer id;

    private String name;

    private String coordinate;

    private String nit;

    private Boolean active;

    private Location location;

    public Hotel() {
    }

    public Hotel(Integer id, String name, String coordinate, String nit, Boolean active, Location location) {
        this.id = id;
        this.name = name;
        this.coordinate = coordinate;
        this.nit = nit;
        this.active = active;
        this.location = location;
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

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
