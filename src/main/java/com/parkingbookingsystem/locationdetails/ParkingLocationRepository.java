package com.parkingbookingsystem.locationdetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ParkingLocationRepository extends JpaRepository<ParkingLocations, Long> {

    @Query("select count(area) from ParkingLocations")
    public Integer checkDataExist();

    @Query("select pl from ParkingLocations pl where pl.area = ?1")
    ParkingLocations findAreaByIdForBooking(String area);
}
