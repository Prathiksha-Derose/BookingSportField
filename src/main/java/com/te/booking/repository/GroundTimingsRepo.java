package com.te.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.booking.entity.GroundTimings;
@Repository
public interface GroundTimingsRepo extends JpaRepository<GroundTimings, String> {

}
