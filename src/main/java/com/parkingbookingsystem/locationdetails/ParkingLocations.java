package com.parkingbookingsystem.locationdetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.TypeDef;
import org.hibernate.type.PostgresUUIDType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.io.Serializable;
import java.util.UUID;

@Entity
@TypeDef(
        name = "pg-uuid",
        defaultForType = UUID.class,
        typeClass = PostgresUUIDType.class
)
public class ParkingLocations{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private int id;

    @JsonProperty("area")
    private String area;

    @JsonProperty("pincode")
    private String pincode;

    @JsonProperty("latitude")
    private String latitude;

    @JsonProperty("longitude")
    private String longitude;


    private Integer slotAvaliable;


    public ParkingLocations() {
    }


    public ParkingLocations(int id, String area, String pincode, String latitude, String longitude, Integer slotAvaliable) {
        this.id = id;
        this.area = area;
        this.pincode = pincode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.slotAvaliable = slotAvaliable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Integer getSlotAvaliable() {
        return slotAvaliable;
    }

    public void setSlotAvaliable(Integer slotAvaliable) {
        this.slotAvaliable = slotAvaliable;
    }
}
