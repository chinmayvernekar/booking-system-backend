package com.parkingbookingsystem.booking;

import com.parkingbookingsystem.locationdetails.ParkingLocations;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.type.PostgresUUIDType;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.UUID;

@Entity
@TypeDef(
        name = "pg-uuid",
        defaultForType = UUID.class,
        typeClass = PostgresUUIDType.class
)
public class SlotBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type = "pg-uuid")
    public UUID bookingId;

    @Type(type = "pg-uuid")
    private UUID userId;

    private String email;

    public Date bookingDate = new Date();

    private Time startTime;

    private Time endTime;

    @Column(name = "location",updatable = true, nullable = true)
    private Integer locationId;

    @ManyToOne()
    @JoinColumn(name = "location" , referencedColumnName = "id",insertable = false,updatable = false)
    private ParkingLocations locations;




    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public ParkingLocations getLocations() {
        return locations;
    }

    public void setLocations(ParkingLocations locations) {
        this.locations = locations;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }
}
