package com.parkingbookingsystem.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SlotBookingRepository extends JpaRepository<SlotBooking,UUID> {

    @Query(value = "select * from slot_booking sb join application_users au on sb.user_id = au.id " +
            "where au.id = ?1",nativeQuery = true)
    List<SlotBooking> allBookingOfLoginUserTillDate(UUID userId);


}