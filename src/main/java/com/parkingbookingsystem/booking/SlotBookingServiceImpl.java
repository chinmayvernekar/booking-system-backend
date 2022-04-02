package com.parkingbookingsystem.booking;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.parkingbookingsystem.ApplicationUserRepository;
import com.parkingbookingsystem.ApplicationUsers;
import com.parkingbookingsystem.locationdetails.ParkingLocationRepository;
import com.parkingbookingsystem.locationdetails.ParkingLocations;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.event.EventRecodingLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SlotBookingServiceImpl implements SlotBookingService {

    @Autowired
    ParkingLocationRepository parkingLocationRepository;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    SlotBookingRepository slotBookingRepository;

    public SlotBooking saveCandidate(SlotBooking booking) {
        return slotBookingRepository.saveAndFlush(booking);
    }

    @Override
    public ResponseEntity<?> bookSlot(SlotBooking booking)
            throws JsonMappingException, JsonProcessingException, JSONException {
        Map<String, Object> map = new HashMap<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        try {
            booking.setEmail(auth.getName());
            System.out.println("EMAIL ID: " + auth.getName());
            booking.setUserId(applicationUserRepository.setUserIdToToken(auth.getName()));
            System.out.println("USER ID: " + applicationUserRepository.setUserIdToToken(auth.getName()));
            slotBookingRepository.save(booking);
            map.put("message", "Booking Sucessfull");
        } catch (Exception e) {
            System.out.println(e);
        }
        return ResponseEntity.ok(map);
    }

    @Override
    public ResponseEntity<?> allBookingOfLoginUserTillDate()
            throws JsonMappingException, JsonProcessingException, JSONException {
        Map<String, Object> map = new HashMap<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        try {
            UUID userId = applicationUserRepository.setUserIdToToken(auth.getName());
            map.put("All The Booking For User " + auth.getName() ,
                    slotBookingRepository.allBookingOfLoginUserTillDate((userId)));
        } catch (Exception e) {
            System.out.println(e);
        }
        return ResponseEntity.ok(map);
    }

    @Override
    public ResponseEntity<?> updateBooking(UUID bookingId, SlotBooking booking)
            throws JsonMappingException, JsonProcessingException, JSONException {
        Map<String, Object> map = new HashMap<>();
        try {
            SlotBooking updateBooking = slotBookingRepository.findById(bookingId).orElseThrow();
            updateBooking.setBookingDate(booking.getBookingDate());
            updateBooking.setStartTime(booking.getStartTime());
            updateBooking.setEndTime(booking.getEndTime());
            updateBooking.setLocationId(booking.getLocationId());
            final SlotBooking bookingUpdated = slotBookingRepository.save(updateBooking);
            map.put("message","Updated Sucessfully.");
        } catch (Exception e) {
            System.out.println(e);
        }
        return ResponseEntity.ok(map);
    }
}
