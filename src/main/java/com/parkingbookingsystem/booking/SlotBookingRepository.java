package com.parkingbookingsystem.booking;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SlotBookingRepository extends JpaRepository<SlotBooking,String> {
}
