package com.parkingbookingsystem.booking;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class SlotBookingController {

    @Autowired
    SlotBookingService slotBookingService;

    @PostMapping("/book-slot")
    public ResponseEntity<?> bookSlot(@RequestBody SlotBooking booking)
            throws JsonMappingException, JsonProcessingException, JSONException{
        return slotBookingService.bookSlot(booking);
    }

    @GetMapping("/bookings")
    public ResponseEntity<?> allBookingOfLoginUserTillDate()
            throws JsonMappingException, JsonProcessingException, JSONException {
        return slotBookingService.allBookingOfLoginUserTillDate();
    }

    @PatchMapping("/booking/{bookingid}/update")
    public ResponseEntity<?> updateBooking(@PathVariable("bookingid") UUID bookingId,@RequestBody SlotBooking booking)
            throws JsonMappingException, JsonProcessingException, JSONException{
       return slotBookingService.updateBooking(bookingId,booking);
   }
}
