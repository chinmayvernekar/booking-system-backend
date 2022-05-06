package com.parkingbookingsystem.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface SlotBookingRepository extends JpaRepository<SlotBooking,UUID> {

    @Query(value = "select * from slot_booking sb join application_users au on sb.user_id = au.id " +
            "where au.id = ?1",nativeQuery = true)
    List<SlotBooking> allBookingOfLoginUserTillDate(UUID userId);

//    @Query("select sb.endTime as endTime from SlotBooking sb")
//    public List<Counts> getSlotBookingByEndTime();


//    @Query("select sb from SlotBooking sb where sb.bookingDate = ?1 and sb.bookingDate <= ?2 ")
    @Query(value = "SELECT sb  FROM slot_booking sb where sb.booking_date <= ?1 and sb.end_time <= ?2",nativeQuery = true)
    public List<SlotBooking> getAllByDate(Date bookingDate,Time endTime);



}