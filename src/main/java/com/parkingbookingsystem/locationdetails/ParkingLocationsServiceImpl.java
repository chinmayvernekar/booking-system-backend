package com.parkingbookingsystem.locationdetails;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.Reader;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@CacheConfig(cacheNames = {"Locations"})
public class ParkingLocationsServiceImpl implements ParkingLocationsService, Serializable {

    private final static Logger logger = LoggerFactory.getLogger(ParkingLocationsServiceImpl.class);

    private static final Gson gson = new Gson();

    @Autowired
    ParkingLocationRepository parkingLocationRepository;


    public void saveDetails(List<ParkingLocations> parking) {
        parkingLocationRepository.saveAll(parking);
    }


    @Override
//  @Cacheable(value = "locations")
    public List<ParkingLocations> listAllLocations() throws JsonMappingException, JsonProcessingException, JSONException {
        Map<String, Object> map = new HashMap<>();
        List<ParkingLocations> locations = null;
        try {
            System.out.println("calling records from DB");
            locations = parkingLocationRepository.findAll();
         } catch (Exception e) {

        }
        return locations;
    }


/*    @Override
    public ResponseEntity<?> listAllLocation()
            throws JsonMappingException, JsonProcessingException, JSONException {
        Map<String, Object> map = new HashMap<>();
        try {
                map.put("Locations",listAllLocations());
        } catch (Exception e) {

        }
        return  ResponseEntity.ok(map);
    }*/
}
