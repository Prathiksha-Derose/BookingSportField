package com.te.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.booking.entity.SportField;
@Repository
public interface SportFieldRepo extends JpaRepository<SportField, Integer>{

}
