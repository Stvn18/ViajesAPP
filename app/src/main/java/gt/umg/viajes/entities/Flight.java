package gt.umg.viajes.entities;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wilver on 28/05/17.
 */

public class Flight extends GenericEntity {

    private Location flyingFrom;
    private Location flyingTo;
    private Date departingDate;
    private Date returningDate;
    private Byte adults;
    private Byte childrens;
    private FlightPreferredClass preferredClass;
    private Airline airline;
    private String description;
    private BigDecimal price;

    public Flight() {
    }

    public Flight(Location flyingFrom, Location flyingTo, Date departingDate, Date returningDate, Byte adults, Byte childrens, FlightPreferredClass preferredClass, Airline airline, String description, BigDecimal price) {
        this.flyingFrom = flyingFrom;
        this.flyingTo = flyingTo;
        this.departingDate = departingDate;
        this.returningDate = returningDate;
        this.adults = adults;
        this.childrens = childrens;
        this.preferredClass = preferredClass;
        this.airline = airline;
        this.description = description;
        this.price = price;
    }

    public Location getFlyingFrom() {
        return flyingFrom;
    }

    public void setFlyingFrom(Location flyingFrom) {
        this.flyingFrom = flyingFrom;
    }

    public Location getFlyingTo() {
        return flyingTo;
    }

    public void setFlyingTo(Location flyingTo) {
        this.flyingTo = flyingTo;
    }

    public Date getDepartingDate() {
        return departingDate;
    }

    public void setDepartingDate(Date departingDate) {
        this.departingDate = departingDate;
    }

    public Date getReturningDate() {
        return returningDate;
    }

    public void setReturningDate(Date returningDate) {
        this.returningDate = returningDate;
    }

    public Byte getAdults() {
        return adults;
    }

    public void setAdults(Byte adults) {
        this.adults = adults;
    }

    public Byte getChildrens() {
        return childrens;
    }

    public void setChildrens(Byte childrens) {
        this.childrens = childrens;
    }

    public FlightPreferredClass getPreferredClass() {
        return preferredClass;
    }

    public void setPreferredClass(FlightPreferredClass preferredClass) {
        this.preferredClass = preferredClass;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
