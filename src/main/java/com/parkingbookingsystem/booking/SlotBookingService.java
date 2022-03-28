package com.parkingbookingsystem.booking;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SlotBookingService {

    ResponseEntity<?> bookSlot(SlotBooking booking)
            throws JsonMappingException, JsonProcessingException, JSONException;

    ResponseEntity<?> allBookingOfLoginUserTillDate()
            throws JsonMappingException, JsonProcessingException, JSONException;
}
