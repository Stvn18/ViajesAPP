package gt.umg.viajes.entities;

/**
 * Created by wilver on 23/04/17.
 */

public class Location {

    private Integer id;
    private String locationName;
    private String graphicCoordinates;
    private boolean active;

    public Location() {
    }

    public Location(Integer id, String locationName, String graphicCoordinates, boolean active) {
        this.id = id;
        this.locationName = locationName;
        this.graphicCoordinates = graphicCoordinates;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getGraphicCoordinates() {
        return graphicCoordinates;
    }

    public void setGraphicCoordinates(String graphicCoordinates) {
        this.graphicCoordinates = graphicCoordinates;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
