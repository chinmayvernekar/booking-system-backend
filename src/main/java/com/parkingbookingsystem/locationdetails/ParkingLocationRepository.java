package com.parkingbookingsystem.locationdetails;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLocationRepository extends JpaRepository<ParkingLocations,Long> {
}
