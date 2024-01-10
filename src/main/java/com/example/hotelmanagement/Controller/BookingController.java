package com.example.hotelmanagement.Controller;

import com.example.hotelmanagement.Model.Booking;
import com.example.hotelmanagement.Service.IBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {
    private IBookingService iBookingService;
    @PostMapping("/{roomID}")
    public ResponseEntity<Booking> addBooking(
            @RequestBody Booking booking,
            @PathVariable Long roomID
            ){
        try{
            Booking newBooking = iBookingService.addBooking(booking, roomID);
            if (newBooking != null){
                return ResponseEntity.ok(newBooking);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
