package com.parkingbookingsystem.booking;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.parkingbookingsystem.ApplicationUserRepository;
import com.parkingbookingsystem.locationdetails.ParkingLocationRepository;
import com.parkingbookingsystem.locationdetails.ParkingLocations;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.event.EventRecodingLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Service
public class SlotBookingServiceImpl implements SlotBookingService{

    @Autowired
    ParkingLocationRepository parkingLocationRepository;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    SlotBookingRepository slotBookingRepository;



    @Override
    public ResponseEntity<?> bookSlot(SlotBooking booking, String area)
            throws JsonMappingException, JsonProcessingException, JSONException{
        Map<String,Object> map = new HashMap<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        try{
            ParkingLocations areaId = parkingLocationRepository.findAreaByIdForBooking(area);
            booking.setEmail(auth.getName());
            System.out.println("EMAIL ID: " + auth.getName());
            booking.setUserId(applicationUserRepository.setUserIdToToken(auth.getName()));
            System.out.println("USER ID: " + applicationUserRepository.setUserIdToToken(auth.getName()));
            booking.addLocation(areaId);
            slotBookingRepository.save(booking);
            map.put("message","Booking Sucessfull");
        }catch (Exception e){
            System.out.println(e);
        }
        return ResponseEntity.ok(map);
    }
}
