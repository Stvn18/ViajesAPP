package gt.umg.viajes.common;

import gt.umg.viajes.dto.SessionDto;
import gt.umg.viajes.entities.Flight;
import gt.umg.viajes.entities.FlightTicket;

/**
 * Created by wilver on 13/04/17.
 */

public class Common {

    private static String urlWs;
    private static SessionDto session;
    private static FlightTicket flightTicket;

    public static void setUrlWs(String urlWs){
        Common.urlWs = urlWs;
    }

    public static String getUrlWs(){
        return Common.urlWs;
    }

    public static SessionDto getSession() {
        return Common.session;
    }

    public static void setSession(SessionDto session) {
        Common.session = session;
    }

    public static FlightTicket getFlightTicket() {
        return flightTicket;
    }

    public static void setFlightTicket(FlightTicket flightTicket) {
        Common.flightTicket = flightTicket;
    }
}
