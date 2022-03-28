package com.parkingbookingsystem.locationdetails;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ParkingLocationsServiceImpl implements ParkingLocationsService {

    @Autowired
    ParkingLocationRepository parkingLocationRepository;

    public void saveDetails(List<ParkingLocations> parking) {


        parkingLocationRepository.saveAll(parking);
    }

    @Override
    public ResponseEntity<?> listAllLocation()
            throws JsonMappingException, JsonProcessingException, JSONException {
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("Locations",parkingLocationRepository.findAll());
        } catch (Exception e) {

        }
        return ResponseEntity.ok(map);
    }
}
