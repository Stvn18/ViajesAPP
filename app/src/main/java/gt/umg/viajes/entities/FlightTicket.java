package gt.umg.viajes.entities;

import java.util.Date;

/**
 * Created by wilver on 31/05/17.
 */

public class FlightTicket extends GenericEntity {

    private static final long serialVersionUID = 7082404985039687326L;

    private Flight flight;
    private Date dateFlight;

    public FlightTicket() {
    }

    public FlightTicket(Flight flight, Date dateFlight) {
        this.flight = flight;
        this.dateFlight = dateFlight;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Date getDateFlight() {
        return dateFlight;
    }

    public void setDateFlight(Date dateFlight) {
        this.dateFlight = dateFlight;
    }

}
