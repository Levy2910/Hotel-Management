package com.example.hotelmanagement.Service;

import com.example.hotelmanagement.Model.Booking;

import java.time.LocalDate;

public interface IBookingService {

    Booking addBooking(Booking booking, Long roomID);
}
