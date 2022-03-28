package com.parkingbookingsystem.booking;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SlotBookingController {

    @Autowired
    SlotBookingService slotBookingService;

    @PostMapping("/book-slot")
    public ResponseEntity<?> bookSlot(@RequestBody SlotBooking booking, @RequestParam(value = "area",required = true) String area)
            throws JsonMappingException, JsonProcessingException, JSONException{
        return slotBookingService.bookSlot(booking,area);
    }
}
