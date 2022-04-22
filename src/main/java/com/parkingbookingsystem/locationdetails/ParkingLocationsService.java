package com.parkingbookingsystem.locationdetails;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ParkingLocationsService {

    public void saveDetails(List<ParkingLocations> parking);


    public List<ParkingLocations> listAllLocations()
            throws JsonMappingException, JsonProcessingException, JSONException;

/*    public ResponseEntity<?> listAllLocation()
           throws JsonMappingException, JsonProcessingException, JSONException;*/
}
