package com.parkingbookingsystem.locationdetails;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class ParkingLocationsDetailsController {

    @Autowired
    ParkingLocationsService parkingLocationsService;

   @GetMapping("/parking-locations")
    public  ResponseEntity<?> listAllLocations(@RequestParam(required=false) Integer pageNumber,
                                                   @RequestParam(required=false) Integer pageSize)
            throws JsonMappingException, JsonProcessingException, JSONException {
        return parkingLocationsService.getLocations(pageNumber,pageSize);
    }

    @GetMapping("/locations-search")
    public ResponseEntity<?> filterDataByAreaName(@RequestParam(required = true) String areaName)
            throws JSONException, JsonProcessingException {
       return parkingLocationsService.filterDataByAreaName(areaName);
    }
}
