package gt.umg.viajes.entities;

/**
 * Created by wilver on 28/05/17.
 */

public class Airline extends GenericEntity {

    private String name;

    public Airline() {
    }

    public Airline(String name, boolean active) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
