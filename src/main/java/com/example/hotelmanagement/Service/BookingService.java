package com.example.hotelmanagement.Service;

import com.example.hotelmanagement.Model.Booking;
import com.example.hotelmanagement.Model.Room;
import com.example.hotelmanagement.Repository.BookingRepository;
import com.example.hotelmanagement.Repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service
@RequiredArgsConstructor
public class BookingService implements IBookingService{
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;

    @Override
    public Booking addBooking(Booking booking, Long roomID) {
//        this is assuming that the booking instance has all the information needed except for the room info...
//        it can be expanded more by checking if the information provided is valid or not...
//        also break down the code pls

        Room room = roomRepository.findById(roomID).orElseThrow();
        List<Booking> bookings = room.getBookings();
        room.setBooked(true);
        bookings.add(booking);

        booking.setRoom(room);

        roomRepository.save(room);
        return bookingRepository.save(booking);
    }
//    delete and update booking as well... easy as f.. i wont do it... next to the next project :3
}
