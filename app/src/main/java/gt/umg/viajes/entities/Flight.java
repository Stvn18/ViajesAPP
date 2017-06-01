package gt.umg.viajes.entities;

import java.sql.Time;

/**
 * Created by wilver on 28/05/17.
 */

public class Flight extends GenericEntity {

    private Location flyingFrom;
    private Location flyingTo;
    private FlightPreferredClass preferredClass;
    private Airline airline;
    private String description;
    private Float adultPrice;
    private Float childPrice;
    private Time departureTime;
    private Time arrivalTime;

    public Flight() {
    }

    public Flight(Location flyingFrom, Location flyingTo, FlightPreferredClass preferredClass, Airline airline, String description, Float adultPrice, Float childPrice, Time departureTime, Time arrivalTime) {
        this.flyingFrom = flyingFrom;
        this.flyingTo = flyingTo;
        this.preferredClass = preferredClass;
        this.airline = airline;
        this.description = description;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
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

    public Float getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(Float adultPrice) {
        this.adultPrice = adultPrice;
    }

    public Float getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(Float childPrice) {
        this.childPrice = childPrice;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
