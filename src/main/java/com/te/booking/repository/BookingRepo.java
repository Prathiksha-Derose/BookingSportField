package com.te.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.booking.entity.Booking;
@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer>{

}
