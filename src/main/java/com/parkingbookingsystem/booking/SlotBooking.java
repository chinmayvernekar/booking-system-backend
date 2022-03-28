package com.parkingbookingsystem.booking;

import com.parkingbookingsystem.locationdetails.ParkingLocations;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.type.PostgresUUIDType;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
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


    private String userId;


    private String email;


    public Date bookingDate = new Date();


    private Time startTime;


    private Time endTime;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "parking_details_booking", joinColumns = @JoinColumn(name = "parking_booking_id"),
            inverseJoinColumns = @JoinColumn(name = "parking_area_id"))
    private Set<ParkingLocations> paking_area_id = new HashSet<>();

    public void addLocation(ParkingLocations area) {
        this.paking_area_id.add(area);
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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

    public Set<ParkingLocations> getPaking_area_id() {
        return paking_area_id;
    }

    public void setPaking_area_id(Set<ParkingLocations> paking_area_id) {
        this.paking_area_id = paking_area_id;
    }
}
