package com.parkingbookingsystem.locationdetails;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class ParkingLocationsDetailsController {

    @Autowired
    ParkingLocationsService parkingLocationsService;

/*    @GetMapping("/parking-locations")
    public ResponseEntity<?> listAllLocation()
            throws JsonMappingException, JsonProcessingException, JSONException {
        return parkingLocationsService.listAllLocation();
    }*/

   @GetMapping("/parking-locations")
    public List<ParkingLocations> listAllLocations()
            throws JsonMappingException, JsonProcessingException, JSONException {
        return parkingLocationsService.listAllLocations();
    }
}
