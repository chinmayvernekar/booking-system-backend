package com.parkingbookingsystem.booking;

import com.parkingbookingsystem.locationdetails.ParkingLocations;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class SlotBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int bookingId;

    private String email;

    public LocalDate bookingDate;

    private Time startTime;

    private Time endTime;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "parking_details_booking", joinColumns = @JoinColumn(name = "parking_booking_id"),
            inverseJoinColumns = @JoinColumn(name = "parking_area_id"))
    private Set<ParkingLocations> paking_area_id = new HashSet<>();




    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
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

    public void setPaking_area_id(Set<ParkingLocations> parking) {
        this.paking_area_id = parking;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
