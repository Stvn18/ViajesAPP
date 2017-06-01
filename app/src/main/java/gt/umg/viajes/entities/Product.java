package gt.umg.viajes.entities;

/**
 * Created by wilver on 31/05/17.
 */

public class Product extends GenericEntity {

    private static final long serialVersionUID = -612286601122230995L;

    private ProductType productType;
    private FlightTicket flightTicket;

    public Product() {
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public FlightTicket getFlightTicket() {
        return flightTicket;
    }

    public void setFlightTicket(FlightTicket flightTicket) {
        this.flightTicket = flightTicket;
    }

}
