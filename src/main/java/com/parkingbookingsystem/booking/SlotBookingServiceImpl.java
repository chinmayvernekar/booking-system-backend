package com.parkingbookingsystem.booking;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.parkingbookingsystem.ApplicationUserRepository;
import com.parkingbookingsystem.ApplicationUsers;
import com.parkingbookingsystem.locationdetails.ParkingLocationRepository;
import com.parkingbookingsystem.locationdetails.ParkingLocations;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;

import com.parkingbookingsystem.locationdetails.ParkingLocationsServiceImpl;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.EventRecodingLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

@Service
@Transactional
public class SlotBookingServiceImpl implements SlotBookingService {

    private final static Logger logger = LoggerFactory.getLogger(SlotBookingServiceImpl.class);

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
            reduceAvaliableSlots(booking);
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


    @Override
    public ResponseEntity<?> cancleBooking(UUID bookingId) throws JsonMappingException, JsonProcessingException, JSONException {
        HashMap<String,String> map = new HashMap<>();
        UUID bookingId1;

        try {
             slotBookingRepository.cancleBooking(bookingId);
             map.put("message","Cancled Sucessfully");
        }
        catch (Exception e){

        }
        return ResponseEntity.ok(map);
    }

    @Override
    public ResponseEntity<?> findAllBookingByUserId(UUID userId) throws JsonMappingException, JsonProcessingException, JSONException {
        List<SlotBooking> user = null;
        try {
          user = slotBookingRepository.findAllByUserId(userId);
        }catch (Exception e){
            e.getMessage();
        }

        return ResponseEntity.ok(user);
    }

    public ResponseEntity<?> reduceAvaliableSlots(SlotBooking booking){
        Map<String, Object> map = new HashMap<>();
        Integer locationsId;
        Integer plId;
        Integer totalSlot;
        Integer reduceAvaliableSlot;
        Integer updateSlotsAvaliable;
        Integer slotAvaliable;
        boolean checkSlotAvaliableNullOrNot = true;
        boolean checkSlotAvaliableNullOrNotQuery;

        try {
            locationsId = booking.getLocationId();
            checkSlotAvaliableNullOrNotQuery = parkingLocationRepository.checkIfSlotAvaliableisNull(locationsId);
                if ( checkSlotAvaliableNullOrNotQuery == checkSlotAvaliableNullOrNot)
                {
                    locationsId = parkingLocationRepository.decrementSlotAvalibality(locationsId);
                    totalSlot = parkingLocationRepository.getTotalSlot(locationsId);
                    updateSlotsAvaliable = totalSlot - 1;
                    parkingLocationRepository.updateTotalSlot(updateSlotsAvaliable, locationsId);
                }
                else {
                    slotAvaliable = parkingLocationRepository.getSlotAvaliable(locationsId);
                    slotAvaliable --;
                    parkingLocationRepository.updateTotalSlot(slotAvaliable, locationsId);

                }

            map.put("message","Updated Sucessfully.");

        }catch (Exception e){
            System.out.println("Error: " + e);
        }
        return ResponseEntity.ok(map);
    }


/*  //  @Scheduled(fixedDelay = 10000)
    public void parseEmployeeObject() throws IOException, ParseException, java.text.ParseException {

        Integer totalSlot;

        Long locationid;

        Integer slotAvaliables;

        Integer updateSlotsAvaliable;

        JSONParser parser = new JSONParser();

        List<SlotBooking> dtos = new ArrayList<>();

        dtos = slotBookingRepository.getSlotBookingByEndTimeAndBookingDate();

        String fileLocation = new File("target//classes//").getAbsolutePath() + "/" +"bookingdto.json";

        Path newFile = Paths.get(fileLocation);
        Files.createDirectories(newFile.getParent());
        System.out.println(newFile.toAbsolutePath().toString());


        FileWriter file = new FileWriter(new File(fileLocation));
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, dtos);

        JSONArray a = (JSONArray) parser.parse(new FileReader(fileLocation));

        for (Object o : a) {

            JSONObject person = (JSONObject) o;

            locationid = (Long) person.get("locationId");
            System.out.println(locationid);
            totalSlot = parkingLocationRepository.getTotalSlot(Math.toIntExact(locationid));
            slotAvaliables = parkingLocationRepository.checkSlotAvaliable(Math.toIntExact(locationid));
            if(slotAvaliables < totalSlot){
                updateSlotsAvaliable =  slotAvaliables + 1;
                parkingLocationRepository.updateTotalSlot(updateSlotsAvaliable, Math.toIntExact(locationid));
            }

        }

    }*/

    @Scheduled(fixedDelay = 50000)
    public void updateSlotWhenBookingIsCompleted(){


        try{
            List<SlotBooking> dtos = new ArrayList<>();
            List<Integer> locations = null;
            dtos = slotBookingRepository.findAll();

            for(SlotBooking location : dtos){
                locations = slotBookingRepository.getSlotBookingByEndTimeAndBookingDate(location.getLocationId());
                logger.info("Locations (getSlotBookingByEndTimeAndBookingDate): " + locations);
            }

            for (Integer locationid: locations) {

                Integer totalSlot = parkingLocationRepository.getTotalSlot(locationid);
                Integer slotAvaliables = parkingLocationRepository.checkSlotAvaliable(locationid);
                Integer  updateSlotsAvaliable = null;
                if(slotAvaliables < totalSlot){
                      updateSlotsAvaliable =  slotAvaliables + 1;
                    parkingLocationRepository.updateTotalSlot(updateSlotsAvaliable, locationid);
                }
                logger.info("SLOT UPDATED SUCESSFULLY!!!");
            }

        }catch (Exception e)
        {
            logger.info("Something went wrong \n" + e);
        }
    }
}
