package gt.umg.viajes.entities;

import java.util.Date;

/**
 * Created by wilver on 17/05/17.
 */

public class HotelDetail {

    private Integer id;
    private Date dateIn;
    private Date dateOut;
    private Integer childrens;
    private Integer adults;
    private Integer bedrooms;
    private String description;
    private Float price;
    private Boolean active;
    private Hotel hotel;

    public HotelDetail() {
    }

    public HotelDetail(Integer id, Date dateIn, Date dateOut, Integer childrens, Integer adults, Integer bedrooms, String description, Float price, Boolean active, Hotel hotel) {
        this.id = id;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.childrens = childrens;
        this.adults = adults;
        this.bedrooms = bedrooms;
        this.description = description;
        this.price = price;
        this.active = active;
        this.hotel = hotel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateIn() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    public Integer getChildrens() {
        return childrens;
    }

    public void setChildrens(Integer childrens) {
        this.childrens = childrens;
    }

    public Integer getAdults() {
        return adults;
    }

    public void setAdults(Integer adults) {
        this.adults = adults;
    }

    public Integer getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
