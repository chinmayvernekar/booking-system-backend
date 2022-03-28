package com.parkingbookingsystem.booking;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;

public interface SlotBookingService {

    ResponseEntity<?> bookSlot(SlotBooking booking,String area)
            throws JsonMappingException, JsonProcessingException, JSONException;
}
