package gt.umg.viajes.entities;

/**
 * Created by wilver on 28/05/17.
 */

public class FlightPreferredClass extends GenericEntity {

    private String name;

    public FlightPreferredClass() {
    }

    public FlightPreferredClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
