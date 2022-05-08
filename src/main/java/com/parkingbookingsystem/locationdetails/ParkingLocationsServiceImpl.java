package com.parkingbookingsystem.locationdetails;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;


import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Autowired
    ParkingLocationRepository parkingLocationRepository;


    public void saveDetails(List<ParkingLocations> parking) {
        parkingLocationRepository.saveAll(parking);
    }


    public List<ParkingLocations> listAllLocations()
            throws JsonMappingException, JsonProcessingException, JSONException {
        List<ParkingLocations> locations = null;
        try {
            locations = parkingLocationRepository.findAll();
         }
        catch (Exception e)
        {

        }
        return locations;
    }

    @Override
    public ResponseEntity<?> getLocations(Integer pageNumber, Integer pageSize)
            throws JsonMappingException, JsonProcessingException, JSONException {
        Page<ParkingLocations> page = null;
        List<ParkingLocations> locations = null;

       try{
        if (pageNumber != null){
            Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by("id"));
            return ResponseEntity.ok(this.parkingLocationRepository.getAllByPageSize(pageable));
        }
        else {
             locations = listAllLocations();
        }
       }catch (Exception e){

       }
       return ResponseEntity.ok(locations);
    }

    @Override
    public ResponseEntity<?> filterDataByAreaName(String area) throws JsonMappingException, JsonProcessingException, JSONException {

        List<ParkingLocations> filterByArea = null;
        try {
            filterByArea = parkingLocationRepository.searchByArea(area);
        }
        catch (Exception e){

        }
        return ResponseEntity.ok(filterByArea);
    }


}
