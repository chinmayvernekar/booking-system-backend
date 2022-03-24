package com.parkingbookingsystem.locationdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLocationsServiceImpl implements ParkingLocationsService{

    @Autowired
    ParkingLocationRepository parkingLocationRepository;

    public void saveDetails(List<ParkingLocations> parking) {
        parkingLocationRepository.saveAll(parking);
    }
}
