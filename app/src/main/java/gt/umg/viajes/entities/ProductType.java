package gt.umg.viajes.entities;

/**
 * Created by wilver on 31/05/17.
 */

public enum ProductType {

    VUELO(1, "Vuelos"), HOTEL(2, "Hoteles"), CRUCERO(3, "Cruceros"), CARRO(4, "Carros");

    private final int id;
    private final String description;

    private ProductType(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

}
