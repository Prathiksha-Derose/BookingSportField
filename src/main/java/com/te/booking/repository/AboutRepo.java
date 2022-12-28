package com.te.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.booking.entity.AboutUser;
@Repository
public interface AboutRepo extends JpaRepository<AboutUser, Integer>{

}
