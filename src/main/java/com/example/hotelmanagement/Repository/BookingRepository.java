package com.example.hotelmanagement.Repository;

import com.example.hotelmanagement.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
